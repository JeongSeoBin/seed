package com.innowireless.web.security;

import com.innowireless.web.api.ErrorCodes;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * 사용자 계정 상태 체크용 class. {@link SecurityConfig} 에서 등록한다.
 */
public class CustomUserDetailsChecker implements UserDetailsChecker {

    @Override
    public void check(UserDetails user) {
        final CustomUser CustomUser = (CustomUser) user;

        if (!CustomUser.isAccountNonLocked()) {
            throw new CustomAuthenticationException(ErrorCodes.ACCOUNT_STATUS_LOCKED);
        }

        if (!CustomUser.isEnabled()) {
            throw new CustomAuthenticationException(ErrorCodes.ACCOUNT_STATUS_DISABLED);
        }
    }
}
