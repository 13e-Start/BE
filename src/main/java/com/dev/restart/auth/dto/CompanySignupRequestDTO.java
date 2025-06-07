package com.dev.restart.auth.dto;

import lombok.Getter;

@Getter
public class CompanySignupRequestDTO {
    private String username;
    private String password;
    private String name;
    private String businessNumber;
    private String telNumber;
    private Integer employeeCount;
    private Integer foundedYear;
    private String companyType;
    private String symbolImageUrl;
}
