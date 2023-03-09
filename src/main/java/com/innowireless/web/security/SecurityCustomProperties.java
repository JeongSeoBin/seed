package com.innowireless.web.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static lombok.AccessLevel.PRIVATE;

@Configuration
@ConfigurationProperties(SecurityCustomProperties.CUSTOM_PREFIX)
@Getter
@Setter
@FieldDefaults(level = PRIVATE)
@ToString
public class SecurityCustomProperties {

    public static final String CUSTOM_PREFIX = "spring.security.custom";

    /**
     * Index Page URL
     */
    String indexPageUrl = "/index.html";

    /**
     * 로그인 URL<br>
     * login-url-pattern을 사용하기 때문에 더이상 사용하지 않는다.<br>
     * 또는 boolean 필드를 추가하여 구분하여 사용하도록 처리해도 된다.
     */
    @Deprecated
    String loginProcessingUrl = "/login";

    /**
     * 로그인 URL prefix
     */
    String loginUrlPrefix = "/api/anon/login";

    /**
     * 로그인 URL 패턴<br>
     * 해당 패턴으로 접근 시 로그인 로직을 수행한다.
     */
    String loginUrlPattern = loginUrlPrefix + "/**";

    /**
     * 로그인 페이지 URL
     */
    String loginPageUrl = "/login.html";

    /**
     * ID/Password 인증 시 ID의 파라미터명
     */
    String usernameParameter = "id";

    /**
     * ID/Password 인증 시 Password의 파라미터명
     */
    String passwordParameter = "pw";

    /**
     * 로그아웃 URL
     */
    String logoutUrl = "/logout";

    /**
     * 접근을 인증 절차 없이 허용하는 URL
     */
    String[] permitAllUrls = {};

    /**
     * 모든 접근을 허용하지 않는 URL
     */
    String[] denyAllUrls = {};

    /**
     * 보안을 위해 기본 정책은 true로 설정되어 있다.<br>
     * - true일 경우, 전달 받은 ID에 대한 사용자가 없거나 Password가 틀렸을 경우
     * 모두 BadCredentialException이 발생한다.<br>
     * - false일 경우, 위와 같은 상황에 UserNotFoundException과 BadCredentialException로 분리된다.
     * <br><br>
     * 프로젝트 특성에 고려하여 설정하도록 한다.
     */
    boolean hideUserNotFoundExceptions = true;

    Session session;

    @Getter
    @Setter
    @FieldDefaults(level = PRIVATE)
    public static class Session {

        /**
         * Set the maximimum number of permitted sessions a user can have open simultaneously.<br>
         * The default value is 1. Use -1 for unlimited sessions.
         */
        int maximumPerUser = 1;

        /**
         * 세션 타임아웃 시간(초)
         */
        int timeout = 3600;

        /**
         * Sets the exceptionIfMaximumExceeded property, which determines whether the user should be
         * prevented from opening more sessions than allowed.
         * If set to true, a SessionAuthenticationException will be raised which means
         * the user authenticating will be prevented from authenticating.
         * If set to false, the user that has already authenticated will be forcibly logged out.
         */
        boolean exceptionIfMaximumExceeded;
    }
}
