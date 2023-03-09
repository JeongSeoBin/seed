package com.innowireless.web.util;

import com.innowireless.web.security.CustomUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class AuthorizationUtil {
    
    public static boolean hasAdminPermission() {
        List<String> authorities = getCurrentUserAuthorities();

        if (authorities == null || authorities.isEmpty()) {
            return false;
        }

        return authorities.contains("ROLE_ADMIN");
    }

    /**
     * 로그인된 사용자의 permission list 가져오기
     *
     * @return
     */
    public static List<String> getCurrentUserAuthorities() {
        Authentication authentication = AuthenticationUtil.getAuthentication();

        if (authentication.getPrincipal() instanceof CustomUser) {
            CustomUser user = (CustomUser) authentication.getPrincipal();

            List<String> authorities = new ArrayList<>();

            for (GrantedAuthority authority : user.getAuthorities()) {
                authorities.add(authority.getAuthority());
            }

            return authorities;
        }

        return null;
    }
}
