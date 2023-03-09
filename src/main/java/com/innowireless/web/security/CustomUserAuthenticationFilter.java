package com.innowireless.web.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowireless.web.api.ErrorCodes;
import com.innowireless.web.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * UsernamePasswordAuthenticationFilter를 상속했지만, 인증 과정은 전혀 다르다.<br>
 * 로그인은 POST된 JSON에 있는 정보로 인증한다.<br>
 * 주의사항:<br>
 * spring security의 UsernamePasswordAuthenticationFilter 클래스에서는 ApiException 던지면 안된다.
 * AuthenticationException(상속 포함) 던져야 로그인 실패 핸들러에서 잡는다.
 * 그리고 그냥 ApiException 던져 보았자 스프링 시큐리티에서 에러를 강제로 500으로 바꿔서 클라이언트로 보낸다.
 * 가능하면 항상 {@link CustomAuthenticationException} 만 throw 하자.
 */
@Slf4j
@RequiredArgsConstructor
public class CustomUserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserService userService;

    // FIXME: 이걸 하는 이유는??
    static {
        disableSslVerification();
    }

    private static void disableSslVerification() {
        // Create a trust manager that does not validate certificate chains
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };

        // Install the all-trusting trust manager
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        final HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }

    private boolean isJsonContentType(final HttpServletRequest request) {
        return StringUtils.contains(request.getHeader("Content-Type"), MediaType.APPLICATION_JSON_VALUE);
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request,
                                                final HttpServletResponse response)
        throws AuthenticationException {

        if (!request.getMethod().equals(HttpMethod.POST.name())) {
            throw new CustomAuthenticationException(ErrorCodes.LOGIN_ERROR,
                "Authentication method not supported: " + request.getMethod());
        }

        try {
            final String username, password;

            if (isJsonContentType(request)) {
                final Map<String, String> info;

                try {
                    final ObjectMapper objectMapper = new ObjectMapper();
                    info = objectMapper.readValue(
                        request.getReader().lines().collect(Collectors.joining()),
                        new TypeReference<>() {
                        }
                    );
                } catch (IOException e) {
                    log.error("LOGIN FAILED: json parse error. failed to get user information", e);
                    throw new CustomAuthenticationException(
                        ErrorCodes.LOGIN_ERROR, "json parse error. failed to get user information");
                }

                username = info.get(super.getUsernameParameter());
                password = info.get(super.getPasswordParameter());
            } else {
                username = super.obtainUsername(request);
                password = super.obtainPassword(request);
            }

            if (StringUtils.isBlank(username)) {
                log.error("LOGIN FAILED: blank user ID");
                throw new CustomAuthenticationException(ErrorCodes.BAD_CREDENTIALS_ERROR);
            }
            if (StringUtils.isBlank(password)) {
                log.error("LOGIN FAILED: blank user password. user ID: {}", username);
                throw new CustomAuthenticationException(ErrorCodes.BAD_CREDENTIALS_ERROR);
            }

            final CustomAuthenticationToken authRequest = new CustomAuthenticationToken(username, password);

            // request에서 필요한 정보를 추출하여 authRequest의 details에 등록한다.
            // SecurityContextHolder.getContext().getAuthentication().getDetails() 메소드를 통하여
            // 등록한 정보들을 읽어 올 수 있다.
            setDetails(request, authRequest);

            return getAuthenticationManager().authenticate(authRequest);
        } catch (Exception e) {
            if (e instanceof AuthenticationException) {
                throw (AuthenticationException) e;
            }

            log.error("generic exception", e);

            throw new CustomAuthenticationException(ErrorCodes.LOGIN_ERROR, "authentication failed");
        }
    }
}
