package com.innowireless.web.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * Spring Security에서 기본적으로 제공하는 토큰 중
 * {@link UsernamePasswordAuthenticationToken ID/Password 기반 토큰}을 상속받아 생성한다.
 * <br>
 * 인증에 필요한 값이 있다면 필드로 추가하여 사용한다. (e.g., isSso=true/false)
 * <br>
 * 인증을 통과하여 생성된 토큰은 {@link SecurityContextHolder}로부터 얻어올 수 있다.
 */
public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public CustomAuthenticationToken(final Object principal, final Object credentials) {
        super(principal, credentials);
    }

    public CustomAuthenticationToken(final Object principal, final Object credentials,
                                     final Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
