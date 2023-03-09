package com.innowireless.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.util.Assert;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

/**
 * ConcurrentSessionFilter(super class)의 역할:
 * 중복 로그인 발생하면, 중복 로그인한 session이 기존 로그인 session의 isExpired만 true로
 * 만들고, 실제로 session invalidate는 하지 않는다. 대신 실제 로그아웃 작업은
 * ConcurrentSessionFilter가 진행(session invalidate) 하며 로그아웃 후의 redirect 나 session
 * expire 메시지를 보내는 역할도 한다.
 * <p>
 * CustomConcurrentSessionFilter의 역할:
 * 실제로 session의 로그아웃 작업만 진행하고 바로 filter chain을 태워버린다.
 * 즉, redirect나 error 메시지는 최종적으로 UnauthrizedEntryPoint class가 처리하게 한다.
 *
 * @author jungeol.park
 */
public class CustomConcurrentSessionFilter extends ConcurrentSessionFilter {

    private final static String iw_realLastAccessedTime = "iw_realLastAccessedTime";

    // session accessedTime 보다 적은 시간내 반복 동작하는 api 생성시 여기에 작성 필요함.
    private final String[] notInitSessionTimeUris = {"getSharedAnalyses", "getNoticeUnreadCount"};
    private final SessionRegistry sessionRegistry;
    private final int sessionTimeout;

    private LogoutHandler[] handlers = new LogoutHandler[]{new SecurityContextLogoutHandler()};

    public CustomConcurrentSessionFilter(final SessionRegistry sessionRegistry,
                                         final int sessionTimeout) {
        super(sessionRegistry);
        this.sessionRegistry = sessionRegistry;
        this.sessionTimeout = sessionTimeout;
    }

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(sessionRegistry, "SessionRegistry required");
    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
        throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final HttpSession session = request.getSession(false);

        if (session != null) {
            final SessionInformation info = sessionRegistry.getSessionInformation(session.getId());

            if (info != null) {
                // 중요:
                // 중복 로그인 발생하면, 중복 로그인한 session이 기존 로그인 session의
                // isExpired만 true 시키고, 실제로 session invalid는 하지 않는다.
                // 그러므로 여기에서 로그아웃를(session invalid) 시켜 줘야한다.
                if (info.isExpired()) {
                    // logout 을 call하여 session을 invalid 시킨다.
                    logout(request, response);
                } else {
                    // session의 accessedTime을 제어하는 것은 거의 불가능으로 보아도 되기 때문에,
                    // api 호출이 있을 경우 uri에 특정 content(SessionTimeNotSave)가 타는 session의
                    // realAccessedTime을 새로 지정하지 않고,
                    // 그렇지 않은 일반적인 api 호출에는 session의 realAccessedTime을 집어 넣어둔다.
                    // 이렇게 보면 기존의 session accessedTime이 필요 없어보이지만,
                    // 아무런 api를 타지 않을 경우 session이 삭제되는 경우가 필요하기 때문에
                    // 기존 session inactiveInterval 와 여기서 사용하는 realAccessedTime과
                    // 일치하게 연장 시켜주는 것이 좋아 보인다.

                    // https://stackoverflow.com/questions/34552875/when-session-is-considered-accessed
                    // 라이브러리 org.apache.tomcat.embed:tomcat-embed-core:9.0.24
                    // catalina.session.StandardSession
                    // methods (endAccess()), properties (lastAccessedTime, thisAccessedTime)
                    // https://stackoverrun.com/ko/q/10951810

                    // sessionTimeoutSec 있는 경우에만 추가된 로직을 타게함.
                    if (sessionTimeout != 0) {
                        final Long realLastAccessedTime = (Long) session.getAttribute(iw_realLastAccessedTime);

                        if (realLastAccessedTime != null &&
                            realLastAccessedTime < (System.currentTimeMillis() - sessionTimeout * 1000)) {
                            logout(request, response);
                        }

                        final String requestUri = ((HttpServletRequest) req).getRequestURI();

                        if (Arrays.stream(notInitSessionTimeUris).allMatch(uri -> !requestUri.contains(uri))) {
                            session.setAttribute(iw_realLastAccessedTime, System.currentTimeMillis());
                        }
                    }
                    sessionRegistry.refreshLastRequest(info.getSessionId());
                }
            }
        }

        chain.doFilter(request, response);
    }

    private void logout(final HttpServletRequest request, final HttpServletResponse response) {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        for (LogoutHandler handler : handlers) {
            handler.logout(request, response, auth);
        }
    }

    @Override
    public void setLogoutHandlers(final LogoutHandler[] handlers) {
        Assert.notNull(handlers, "handlers can not be null");
        this.handlers = handlers;
    }
}
