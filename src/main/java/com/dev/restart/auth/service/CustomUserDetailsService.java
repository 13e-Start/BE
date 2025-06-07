package com.dev.restart.auth.service;

import com.dev.restart.company.servie.CompanyService;
import com.dev.restart.personal.servie.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final CompanyService companyService;

    @Override
    public UserDetails loadUserByUsername(String compositeKey) throws UsernameNotFoundException {
        String[] parts = compositeKey.split(":");
        String type = parts[0];
        String id = parts[1];

        switch (type) {
            case "USER":
                return UserPrincipal.from(userService.getUserById(id));

            case "COMPANY":
                return CompanyPrincipal.from(companyService.getCompanyById(id));

            case "ADMIN":
//                return HospitalPrincipal.from(hospitalService.getHospitalEntityById(id));

            default:
                throw new UsernameNotFoundException("알 수 없는 사용자 유형");
        }
    }

}