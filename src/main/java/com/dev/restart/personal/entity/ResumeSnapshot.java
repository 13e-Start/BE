package com.dev.restart.personal.entity;

import com.dev.restart.metadata.entity.HighestLevelEducation;
import com.dev.restart.personal.entity.ResumeHasEmployeeBenefits.ResumeHasEmployeeBenefit;
import com.dev.restart.personal.entity.ResumeHasPositions.ResumeHasPosition;
import com.dev.restart.personal.entity.ResumeHasRegions.ResumeHasRegion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "resume_snapshot")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name",nullable = false, length = 20)
    private String name;

    @Column(name = "user_birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "user_tel_number", nullable = false, length = 45)
    private String telNumber;

    @Column(name = "user_address", length = 100)
    private String address;

    @Column(name = "user_profile_image_url", length = 100)
    private String profileImageUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "highest_level_education_id", nullable = false)
    private HighestLevelEducation highestLevelEducation;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resume_snapshot_id")
    private Set<ResumeHasEmployeeBenefit> desiredEmployeeBenefits;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resume_snapshot_id")
    private Set<ResumeHasPosition> desiredPositions;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resume_snapshot_id")
    private Set<ResumeHasRegion> desiredRegions;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resume_snapshot_id")
    @Builder.Default
    private Set<ResumeCareer> careers = new HashSet<>();


    private Integer version;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 100)
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_active", nullable = false, columnDefinition = "true")
    private Boolean isActive;

}
