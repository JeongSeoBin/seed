package com.innowireless.web.security;

import lombok.Getter;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

@Getter
public class CustomUser implements UserDetails, CredentialsContainer {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private String userId;
    private String password;

    private final Set<GrantedAuthority> authorities;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    /**
     * 추가된 필드
     */
    private String userName;

    // Constructor
    public CustomUser(
        final Collection<? extends GrantedAuthority> authorities,
        final String userId,
        final String password,
        final boolean accountNonExpired,
        // 추가된 필드
        final String userName
    ) {
        if (((userId == null) || "".equals(userId)) || (password == null)) {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }

        this.userId = userId;
        this.password = password;
        this.enabled = true;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = true;
        this.accountNonLocked = true;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));

        // 추가된 필드
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    //---------------------------------------------------------------------------------------------
    // super class의 메소드 구현 부분
    //---------------------------------------------------------------------------------------------

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    //---------------------------------------------------------------------------------------------
    // Authority sort 관련 메소드
    // org.springframework.security.core.userdetails.User 클래스에서 copy 함
    //---------------------------------------------------------------------------------------------

    private static SortedSet<GrantedAuthority> sortAuthorities(
        final Collection<? extends GrantedAuthority> authorities) {

        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        // Ensure array iteration order is predictable (as per UserDetails.getAuthorities()
        // contract and SEC-717)
        final SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(new AuthorityComparator());

        for (final GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority,
                "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {

        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        @Override
        public int compare(final GrantedAuthority g1, final GrantedAuthority g2) {
            // Neither should ever be null as each entry is checked before adding it to the set.
            // If the authority is null, it is a custom authority and should precede others.
            if (g2.getAuthority() == null) {
                return -1;
            }

            if (g1.getAuthority() == null) {
                return 1;
            }

            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }

    //---------------------------------------------------------------------------------------------
    // Object 클래스의 메소드 Override
    //---------------------------------------------------------------------------------------------

    @Override
    public boolean equals(final Object rhs) {
        if (rhs instanceof CustomUser) {
            return this.userId.equals(((CustomUser) rhs).userId);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.userId.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("UserId: ").append(this.userId).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("Enabled: ").append(this.enabled).append("; ");
        sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
        sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
        sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");
        sb.append("UserName: ").append(this.userName).append("; ");

        if (!authorities.isEmpty()) {
            sb.append("Granted Authorities: ");

            boolean first = true;
            for (final GrantedAuthority auth : authorities) {
                if (!first) {
                    sb.append(",");
                }

                first = false;

                sb.append(auth);
            }
        } else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();
    }
}

