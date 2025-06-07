package com.dev.restart.auth.dto;

import lombok.Getter;

@Getter
public class SignupRequestDto {

    private String username;
    private String password;
    private String name;
    private String birthDate;
    private String telNumber;
    private String address;
    private String profileImageUrl;
}
