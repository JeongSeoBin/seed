package com.innowireless.web.security;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

/**
 * <h3>인증을 수행한다.</h3>
 * <ol>
 *     <li>{@link CustomUserDetailsService#loadUserByUsername(String) 사용자 정보를 조회}한다.</li>
 *     <li>{@link CustomUserDetailsChecker#check(UserDetails) 사용자가 유효한 지 판단}한다.</li>
 *     <li>{@link DaoAuthenticationProvider ID/Password 기반 인증}이라면 Password 일치하는 지 판단한다.</li>
 * </ol>
 */
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    public Authentication authenticate(final Authentication authentication)
        throws AuthenticationException {

        Assert.isInstanceOf(CustomAuthenticationToken.class, authentication,
            () -> messages.getMessage(
                "CustomAuthenticationProvider.onlySupports",
                "Only CustomAuthenticationToken is supported"));

        return super.authenticate(authentication);
    }
}
