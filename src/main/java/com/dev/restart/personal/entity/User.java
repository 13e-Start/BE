package com.dev.restart.personal.entity;

import com.dev.restart.metadata.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    private LocalDate birthDate;

    @Column(name = "tel_number", nullable = false, length = 45)
    private String telNumber;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "profile_image_url", length = 100)
    private String profileImageUrl;

    @Column(name = "is_active", nullable = false, columnDefinition = "true")
    private Boolean isActive;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<RecruitApplicant> recruitApplicants = new HashSet<>();
}
