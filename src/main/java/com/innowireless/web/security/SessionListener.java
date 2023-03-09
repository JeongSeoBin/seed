package com.innowireless.web.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class SessionListener extends HttpSessionEventPublisher {

    public static final String USER_ID_KEY = "USER_ID";

    private final SecurityCustomProperties properties;

    @Override
    public void sessionCreated(final HttpSessionEvent event) {
        event.getSession().setMaxInactiveInterval(properties.getSession().getTimeout());
        super.sessionCreated(event);
    }

    @Override
    public void sessionDestroyed(final HttpSessionEvent event) {
        final HttpSession session = event.getSession();
        final String sessionId = session.getId();

        // 로그인 성공핸들러에서 기록한 userId 가져온다.
        final String userId = (String) session.getAttribute(USER_ID_KEY);

        // user id가 없으면 로그인된 사용자 session이 아니므로 그냥 종료한다.
        if (StringUtils.isEmpty(userId)) {
            super.sessionDestroyed(event);

            return;
        }

        log.info("Session destroyed. UserID = {}, SessionID = {}", userId, sessionId);

        super.sessionDestroyed(event);
    }
}
