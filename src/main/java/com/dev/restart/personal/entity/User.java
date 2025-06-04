package com.dev.restart.personal.entity;

import com.dev.restart.metadata.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    private String id;

    @Column(nullable = false, length = 45)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDateTime birthDate;

    @Column(name = "tel_number", nullable = false, length = 45)
    private String telNumber;

    @Column(length = 100)
    private String address;

    @Column(name = "profile_image_url", length = 100)
    private String profileImageUrl;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}
