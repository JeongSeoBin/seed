package com.innowireless.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowireless.web.api.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * LogoutSuccessHandler, LoginSuccessHandler, LoginFailureHandler 들을 모아놓은 클래스 이다.
 *
 * @author jungeol.park
 */
@Component
public class CustomLoginLogoutHandler {

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new LogoutSuccessHandler();
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public LoginFailureHandler loginFailureHandler() {
        return new LoginFailureHandler();
    }

    /**
     * LogoutSuccessHandler를 따로 정의하여 등록하지 않으면, 기본 handler가 redirect를 진행한다.
     * 기본 handler의 기능이 필요하지 않기 때문에 빈 LogoutSuccessHandler를 사용한다.
     */
    @Slf4j
    public static class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

        @Override
        public void onLogoutSuccess(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final Authentication auth) throws IOException {
            log.debug("logout success.");

            PrintWriter out = response.getWriter();
            out.print("{}");
            out.flush();
            out.close();
        }
    }

    @Slf4j
    public static class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

        @Override
        public void onAuthenticationSuccess(final HttpServletRequest request,
                                            final HttpServletResponse response,
                                            final Authentication auth) {
            try {
                HttpSession session = request.getSession();
                String sessionId = session.getId();
                String userId = auth.getName();

                // 세션 소멸시 사용자 ID를 알기 위하여 기록
                session.setAttribute(SessionListener.USER_ID_KEY, userId);
                log.debug("login user: {}, sessionId: {}", userId, sessionId);

                // FIXME: 로그인 처리 로직 추가
                response.setStatus(HttpServletResponse.SC_OK);

                PrintWriter out = response.getWriter();
                out.print(1);
                out.flush();
                out.close();
            } catch (Exception e) {
                // 여기까지 왔으면 이미 로그인은 성공 되었기 때문에
                // Exception 던지면 UI에서 로그인은 실패 되고 시스템에서는 로그인 되는
                // 이상한 상황이 된다. 그냥 에러만 찍어주자.
                log.error("handle login success error.", e);
            }
        }
    }

    @Slf4j
    public static class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(final HttpServletRequest request,
                                            final HttpServletResponse response,
                                            final AuthenticationException exception)
            throws IOException {

            log.debug("login failed.");

            // UserAuthenticationService 클래스에서 throw 되는 예외들은
            // InternalAuthenticationServiceException으로 감싸여져서 온다.
            // 그래서 내부의  예외를 빼내야 된다.
            // 자세한 사항은 UserAuthenticationService 클래스의 메인 주석 참고
            AuthenticationException newException = exception;

            if ((exception instanceof InternalAuthenticationServiceException) && (exception.getCause() != null)) {
                newException = (AuthenticationException) exception.getCause();
            }

            // json response를 직접 만들어서 전달한다.
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            Map<String, Object> map = new HashMap<>();
            if (newException instanceof BadCredentialsException) {
                map.put("code", ErrorCodes.BAD_CREDENTIALS_ERROR);
            } else if (newException instanceof UsernameNotFoundException) {
                // 계정 id 틀릴 때
                map.put("code", ErrorCodes.USER_NAME_NOT_FOUND);
            } else if (newException instanceof CustomAuthenticationException) {
                // 인증과정에서 throw 되는 exception 처리
                final CustomAuthenticationException e = (CustomAuthenticationException) newException;

                map.put("code", e.getCode());
                map.put("message", e.getMessage());
            } else {
                // 그 외 상황은 상세 메시지를 클라이언트에 내려준다.
                map.put("code", ErrorCodes.LOGIN_ERROR);
                map.put("message", newException.getClass() + ": " + newException.getMessage());
            }

            ObjectMapper om = new ObjectMapper();
            String jsonStr = om.writeValueAsString(map);

            PrintWriter out = response.getWriter();
            out.print(jsonStr);
            out.flush();
            out.close();
        }
    }
}
