package com.innowireless.web.util;

import com.innowireless.web.api.ApiException;
import com.innowireless.web.api.ErrorCodes;
import com.innowireless.web.security.CustomUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class AuthenticationUtil {

    public static Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null)
            throw new ApiException(ErrorCodes.NEED_LOGIN);

        if (!authentication.isAuthenticated())
            throw new ApiException(ErrorCodes.NEED_LOGIN);

        return authentication;
    }

    public static boolean isLoggedin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            return false;
        return true;
    }

    /**
     * 익명 사용자일 경우에도 authentication token이 존재하기 때문에
     * anonymous token인지 아닌지를 판단해 알려준다.
     *
     * @return boolean
     */
    public static boolean isUserLoggedin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null)
            return false;
        return !(authentication instanceof AnonymousAuthenticationToken);
    }

    /**
     * 로그인된 현재 사용자의 ID를 가져온다.
     *
     * @return String
     */
    public static String getCurrentUserId() {
        Authentication authentication = getAuthentication();
        return authentication.getName();
    }

    public static List<String> getCurrentUserRoles() {
        Authentication authentication = getAuthentication();
        Object[] authorities = authentication.getAuthorities().toArray();
        if (authorities.length == 0)
            throw new ApiException(ErrorCodes.INTERNAL_ERROR, "Unknown user role");

        List<String> roles = new ArrayList<>();
        for (Object authority : authorities) {
            roles.add(((GrantedAuthority) authority).getAuthority());
        }

        return roles;
    }

    public static boolean isUserExpired() {
        Authentication authentication = getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUser) {
            CustomUser user = (CustomUser) authentication.getPrincipal();
            return !user.isAccountNonExpired();
        }
        // 이런 일은 없다.
        return false;
    }
}
