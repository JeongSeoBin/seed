package com.innowireless.web.security;

import com.innowireless.web.api.ErrorCodes;
import com.innowireless.web.mapper.UserMapper;
import com.innowireless.web.security.CustomLoginLogoutHandler.LoginFailureHandler;
import com.innowireless.web.util.DBUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import java.time.LocalDate;
import java.util.*;

/**
 * 사용자 인증을 위하여 DB에서 사용자 정보를 읽어오는 역할을 한다. <br>
 * <br>
 * 구현 주의 사항.<br>
 * UserAuthenticationService 클래스에서 throw 하는 exception은 {@link UsernameNotFoundException}
 * 을 제외하면 모두 spring security에서 {@link InternalAuthenticationServiceException} 예외로
 * 포장하여 전달하며 최종적으로 로그인 실패 핸들러에({@link LoginFailureHandler}) 도달한다. <br>
 * 그러므로 로그인 실패 핸들러에서 exception의 내용을 적절하게 분석하여 클라이언트에 내려주어야 한다. <br>
 * <br>
 * 주의! <br>
 * 관리 편의상 UserAuthenticationService에서는 {@link UsernameNotFoundException}과
 * {@link CustomAuthenticationException} 두 가지만 throw 하자.
 */
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService extends JdbcDaoImpl {

    private final UserMapper userMapper;

    // 주의: UserDetailsService는 username 만 가지고 동작해야 한다. request 같은 걸 보려 하지 말 것.

    /**
     * ID/Password 기반 사용자 정보를 조회한다.
     *
     * @param username 사용자 ID
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(final String username) {
        final List<UserDetails> users = loadUsersByUsername(username);
        final CustomUser user = (CustomUser) users.get(0); // contains no GrantedAuthority[]

        final List<GrantedAuthority> auths = loadUserAuthorities(username);
        final Set<GrantedAuthority> dbAuthsSet = new HashSet<>(auths);
        final List<GrantedAuthority> dbAuths = new ArrayList<>(dbAuthsSet);

        if (dbAuths.isEmpty()) {
            log.error("LOGIN FAILED: no user role. user ID = {}", username);

            throw new CustomAuthenticationException(ErrorCodes.NO_USER_ROLE);
        }

        return createUserDetails(username, user, dbAuths);
    }

    @Override
    protected List<UserDetails> loadUsersByUsername(final String username) {
        final List<Map<String, Object>> rows = userMapper.getUserForAuthentication(username);

        if (rows == null || rows.isEmpty()) {
            log.error("LOGIN FAILED: user not found. user ID = {}", username);

            final String message = "user not found";

            throw new UsernameNotFoundException(
                messages.getMessage("UserAuthenticationService." + message,
                    new Object[]{username}, "Username {0} " + message));
        }

        final List<UserDetails> users = new ArrayList<>();

        for (final Map<String, Object> row : rows) {
            final LocalDate expireDate = DBUtil.toLocalDate(row.get("expire_date"));
            final CustomUser user = new CustomUser(
                AuthorityUtils.NO_AUTHORITIES,
                DBUtil.toNotNullString(row.get("user_id")),
                DBUtil.toNotNullString(row.get("password")),
                // LocalDate.now().compareTo(expireDate) <= 0, 만료일 체크를 여기서 하지 않는다.
                // 만료일이 null 이더라도 로그인이 가능해야함.
                true,
                DBUtil.toNotNullString(row.get("user_name")));

            users.add(user);
        }

        return users;
    }

    @Override
    protected UserDetails createUserDetails(final String userId, final UserDetails userFromUserQuery,
                                            final List<GrantedAuthority> combinedAuthorities) {
        if (!(userFromUserQuery instanceof CustomUser)) {
            throw new CustomAuthenticationException(ErrorCodes.LOGIN_ERROR, "userFromUserQuery");
        }

        final CustomUser userFromDB = (CustomUser) userFromUserQuery;

        return new CustomUser(
            combinedAuthorities,
            userId,
            userFromDB.getPassword(),
            userFromDB.isAccountNonExpired(),
            userFromDB.getUserName());
    }

    @Override
    protected List<GrantedAuthority> loadUserAuthorities(final String username) {
        final List<Map<String, Object>> rows = userMapper.getUserRoles(username);

        if (rows.isEmpty()) {
            throw new CustomAuthenticationException(ErrorCodes.NOT_FOUND, "Role not found");
        }

        final List<GrantedAuthority> authorities = new ArrayList<>();

        for (Map<String, Object> userRole : rows) {
            authorities.add(new SimpleGrantedAuthority(getRolePrefix() + userRole.get("ROLE_ID")));
        }

        return authorities;
    }
}
