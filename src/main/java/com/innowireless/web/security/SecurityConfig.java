package com.innowireless.web.security;

import com.innowireless.web.api.ErrorCodes;
import com.innowireless.web.mapper.UserMapper;
import com.innowireless.web.security.CustomLoginLogoutHandler.LoginFailureHandler;
import com.innowireless.web.security.CustomLoginLogoutHandler.LoginSuccessHandler;
import com.innowireless.web.security.CustomLoginLogoutHandler.LogoutSuccessHandler;
import com.innowireless.web.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.SpringDocConfigProperties;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@Slf4j
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityCustomProperties properties;
    private final DataSource dataSource;
    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailureHandler loginFailureHandler;
    private final LogoutSuccessHandler logoutSuccessHandler;
    private final UserService userService;
    private final UserMapper userMapper;

    private final SpringDocConfigProperties springDocConfigProperties;
    private final SwaggerUiConfigParameters swaggerUiConfigParameters;

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        //-----------------------------------------------------------------------------------------
        // Authentication setting
        //-----------------------------------------------------------------------------------------
        http
            .logout()
            .invalidateHttpSession(true)
            .logoutUrl(properties.getLogoutUrl())
            .logoutSuccessHandler(logoutSuccessHandler)
            .permitAll()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(customAuthenticationEntryPoint())
            .and()
            .addFilterAt(
                customUserAuthenticationFilter(
                    authenticationManager(http.getSharedObject(AuthenticationConfiguration.class))),
                UsernamePasswordAuthenticationFilter.class);

        //-----------------------------------------------------------------------------------------
        // Authorization setting
        //-----------------------------------------------------------------------------------------
        http.authorizeRequests().antMatchers(properties.getLoginPageUrl()).permitAll();

        http
            .authorizeRequests()
            .antMatchers(properties.getPermitAllUrls()).permitAll()
            .antMatchers("/api/admin/**").access("@permissionEvaluator.isAdmin()")
            .antMatchers(new String[]{
                springDocConfigProperties.getApiDocs().getPath() + org.springdoc.core.Constants.ALL_PATTERN,
                swaggerUiConfigParameters.getPath(),
                org.springdoc.core.Constants.SWAGGER_UI_PREFIX + org.springdoc.core.Constants.ALL_PATTERN
            }).access("@permissionEvaluator.isAdmin()")
            .antMatchers(properties.getDenyAllUrls()).denyAll()
            .anyRequest().authenticated(); // antMatchers에 없는 url은 모두 인증 해야 한다.
        // 주의: 인증이 필요한 URL 중 브라우저로 직접 navigate하는 URL은
        //   customAuthenticationEntryPoint() 에서 redirect 시킬 것.

        //-----------------------------------------------------------------------------------------
        // Session management
        //-----------------------------------------------------------------------------------------

        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .sessionAuthenticationStrategy(customSessionAuthenticationStrategy())
            .and()
            .addFilter(customConcurrentSessionFilter());

        //-----------------------------------------------------------------------------------------
        // csrf protection
        //-----------------------------------------------------------------------------------------

        http.csrf().disable();

        //-----------------------------------------------------------------------------------------
        // http security headers
        //-----------------------------------------------------------------------------------------

        final String cspHeader = "Content-Security-Policy";
        final String cspSb = "default-src 'unsafe-inline' 'self'; " +
            "font-src * data:; " +
            "img-src * data: blob:;" +
            // blob: 은 high chart export 에서 사용하기 때문에 추가했다.
            "connect-src *; " +
            // connect-src를 zuul proxy LTAS에서 사용하기 때문에 추가했다.
            "script-src 'unsafe-inline' 'unsafe-eval' 'self';";
        // inline script를 써야 하는 경우(#616 분석연동)가 생겨 'unsafe-inline' 추가함.
        http
            .headers()
            .addHeaderWriter(new StaticHeadersWriter(cspHeader, cspSb));

        //-----------------------------------------------------------------------------------------
        // X-Frame-Options
        // HTTP 응답 헤더이고 해당 페이지를 <frame> 또는 <iframe>, <object> 에서 렌더링할 수 있는지 여부를
        // 나타냄. deny, sameorigin, allow-from https://example.com/ 세가지 형태의 값이 있음
        //-----------------------------------------------------------------------------------------

        // iframe에 랜더링 가능 여부를 sameorigin으로 설정
        http
            .headers()
            .frameOptions().sameOrigin();

        return http.build();
    }

    @Bean
    public AuthenticationEntryPoint customAuthenticationEntryPoint() {
        return (request, response, authException) -> {
            final String uri = request.getRequestURI();

            // TODO auth를 해야 하는 URL들을 정리하자.
            if (StringUtils.equals(uri, "/") ||
                StringUtils.equals(uri, properties.getIndexPageUrl()) ||
                StringUtils.equals(uri, swaggerUiConfigParameters.getPath())) {

                final String loginPageUrl = properties.getLoginPageUrl();

                // 브라우저로 직접 navigate하는 URL은 로그인 쪽으로 redirect.
                response.sendRedirect(loginPageUrl.startsWith("/") ?
                    request.getContextPath() + loginPageUrl : loginPageUrl);
            } else {
                // 그외 모든 요청은 JSON 형태의 error response를 직접 만들어서 전달한다.
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                final String data = "{\"code\": " + ErrorCodes.NEED_LOGIN + "}";

                final PrintWriter out = response.getWriter();
                out.print(data);
                out.flush();
                out.close();
            }
        };
    }

    @Bean
    public CustomUserAuthenticationFilter customUserAuthenticationFilter(
        final AuthenticationManager authenticationManager) throws Exception {

        // http://stackoverflow.com/questions/24122586/how-to-represent-the-spring-security-custom-filter-using-java-configuration
        final CustomUserAuthenticationFilter filter = new CustomUserAuthenticationFilter(userService);
        filter.setAuthenticationManager(authenticationManager);
        filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(properties.getLoginUrlPattern()));
        filter.setUsernameParameter(properties.getUsernameParameter());
        filter.setPasswordParameter(properties.getPasswordParameter());
        filter.setAuthenticationSuccessHandler(loginSuccessHandler);
        filter.setAuthenticationFailureHandler(loginFailureHandler);
        filter.setSessionAuthenticationStrategy(customSessionAuthenticationStrategy());

        return filter;
    }

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration configuration)
        throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider customAuthenticationProvider() {
        final CustomAuthenticationProvider provider = new CustomAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService());
        provider.setPreAuthenticationChecks(customUserDetailsChecker());
        provider.setPasswordEncoder(customPasswordEncoder());
        provider.setHideUserNotFoundExceptions(properties.isHideUserNotFoundExceptions());

        return provider;
    }

    @Bean
    public CustomUserDetailsService customUserDetailsService() {
        final CustomUserDetailsService userDetailsService = new CustomUserDetailsService(userMapper);
        userDetailsService.setDataSource(dataSource);
        userDetailsService.setRolePrefix("ROLE_"); // "ROLE_"로 지정해야 권한체크가 정상동작한다.
        userDetailsService.setEnableGroups(false);

        return userDetailsService;
    }

    @Bean
    public CustomUserDetailsChecker customUserDetailsChecker() {
        return new CustomUserDetailsChecker();
    }

    /**
     * TODO: 프로젝트에 맞게 PasswordEncoder를 설정하자.
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder customPasswordEncoder() {
        /*
        // 참고: https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/#pe-dpe
        String idForEncode = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(idForEncode, new BCryptPasswordEncoder());
        encoders.put("noop", NoOpPasswordEncoder.getInstance());  // test용.
        // ATAS 1.0 USER 정보를 허용하기 위해서 사용하였다.
        // 위 참고의 StandardPasswordEncoder의 sha265표준과 다른 형태라서 md-sha256 이라고 만들었다.
        encoders.put("md-sha256", new MessageDigestPasswordEncoder("SHA-256"));
        return new DelegatingPasswordEncoder(idForEncode, encoders);
        */
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * session의 concurrent control, fixation attack 방지, session registry등록
     * 관련 기능을 정의한다.
     */
    @Bean
    public CompositeSessionAuthenticationStrategy customSessionAuthenticationStrategy() {
        // Concurrent session 관리
        final ConcurrentSessionControlAuthenticationStrategy concurrentSessionCtrlAuthStrategy =
            new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
        concurrentSessionCtrlAuthStrategy.setMaximumSessions(
            properties.getSession().getMaximumPerUser());
        concurrentSessionCtrlAuthStrategy.setExceptionIfMaximumExceeded(
            properties.getSession().isExceptionIfMaximumExceeded());

        // session fixation attack 방지
        final SessionFixationProtectionStrategy sessionFixationProtectionStrategy =
            new SessionFixationProtectionStrategy();

        // SessionRegistry() Bean에 인증 후 등록해 주는 역할
        final RegisterSessionAuthenticationStrategy registerSessionStrategy =
            new RegisterSessionAuthenticationStrategy(sessionRegistry());

        // 위 3개의 strategy를 CompositeSessionAuthenticationStrategy에 묶는다.
        return new CompositeSessionAuthenticationStrategy(
            Arrays.asList(
                concurrentSessionCtrlAuthStrategy,
                sessionFixationProtectionStrategy,
                registerSessionStrategy));
    }

    /**
     * SessionRegistry Bean - 서버의 active한 session 리스트를 관리 해주는 역할을 한다.
     * 즉, SessionRegistry를 이용하여 현재 서버에 연결된 client의 count 혹은 기타
     * 정보를 알아볼 수 있다.
     */
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    /**
     * 여기서 return하는 filter는 중복 로그인으로 인한 session expire 상황때 기존 session
     * 유저를 로그아웃(session invalidate) 시키는 동작만 한다. 그외 redirect나 error 처리는
     * UnauthorizedEntryPoint class가 진행한다.
     */
    @Bean
    public ConcurrentSessionFilter customConcurrentSessionFilter() {
        return new CustomConcurrentSessionFilter(sessionRegistry(), properties.getSession().getTimeout());
    }
}
