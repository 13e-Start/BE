package com.dev.restart.auth.service;

import com.dev.restart.auth.enums.Role;
import com.dev.restart.company.entity.Company;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CompanyPrincipal implements UserDetails {

    private final Company company;
    private final String role;

    public CompanyPrincipal(Company company){
        this.company = company;
        this.role = Role.COMPANY.name();
    }

    public static CompanyPrincipal from(Company company){
        return new CompanyPrincipal(company);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return company.getUsername();
    }

    @Override public String getPassword() { return " "; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
