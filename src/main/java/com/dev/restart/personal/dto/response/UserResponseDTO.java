package com.dev.restart.personal.dto.response;

import com.dev.restart.personal.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class UserResponseDTO {

    private String id;
    private String name;
    private LocalDate birthDate;
    private String telNumber;
    private String address;
    private String profileImageUrl;

    public static UserResponseDTO from(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .birthDate(user.getBirthDate())
                .telNumber(user.getTelNumber())
                .address(user.getAddress())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }
}
