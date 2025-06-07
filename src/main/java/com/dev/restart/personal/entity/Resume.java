package com.dev.restart.personal.entity;

import com.dev.restart.metadata.entity.BaseEntity;
import com.dev.restart.metadata.entity.HighestLevelEducation;
import com.dev.restart.personal.entity.ResumeHasEmployeeBenefits.ResumeHasEmployeeBenefit;
import com.dev.restart.personal.entity.ResumeHasPositions.ResumeHasPosition;
import com.dev.restart.personal.entity.ResumeHasRegions.ResumeHasRegion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "resume")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "highest_level_education_id", nullable = false)
    private HighestLevelEducation highestLevelEducation;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<ResumeHasEmployeeBenefit> desiredEmployeeBenefits = new HashSet<>();

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<ResumeHasPosition> desiredPositions = new HashSet<>();

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<ResumeHasRegion> desiredRegions = new HashSet<>();

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<ResumeCareer> careers = new HashSet<>();

    @Column(nullable = false)
    private Integer version;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 100)
    private String content;

    @Column(name = "is_active", nullable = false, columnDefinition = "true")
    private Boolean isActive;


    public ResumeSnapshot toSnapshot(User user) {
        return ResumeSnapshot.builder()
                .name(user.getName())
                .birthDate(user.getBirthDate())
                .telNumber(user.getTelNumber())
                .address(user.getAddress())
                .profileImageUrl(user.getProfileImageUrl())
                .highestLevelEducation(this.highestLevelEducation)
                .title(this.title)
                .content(this.content)
                .version(this.version)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public void update(String title, String content, HighestLevelEducation highestLevelEducation) {
        this.title = title;
        this.content = content;
        this.highestLevelEducation = highestLevelEducation;
    }
}
