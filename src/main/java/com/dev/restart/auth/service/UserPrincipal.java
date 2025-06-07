package com.dev.restart.auth.service;

import com.dev.restart.auth.enums.Role;
import com.dev.restart.personal.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private final User user;
    private final String role;

    public UserPrincipal(User user){
        this.user = user;
        this.role = Role.USER.name();
    }

    public static UserPrincipal from(User user){
        return new UserPrincipal(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }


    // 계정 상태 검사
    @Override public String getPassword() { return " "; }
    @Override public boolean isAccountNonExpired() { return true; } // return !user.isWithdrawn();
    @Override public boolean isAccountNonLocked() { return true; } // return !user.isLocked();
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; } // return user.isActive();
}
