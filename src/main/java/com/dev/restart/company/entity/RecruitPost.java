package com.dev.restart.company.entity;

import com.dev.restart.company.dto.request.RecruitPostRequestDTO;
import com.dev.restart.company.entity.RecruitHasBenefit.RecruitHasBenefit;
import com.dev.restart.metadata.entity.*;
import com.dev.restart.personal.entity.RecruitApplicant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "recruit_post")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "recruitPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RecruitApplicant> applicants;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_category_id", nullable = false)
    private PositionCategory positionCategory;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "highest_level_education_id", nullable = false)
    private HighestLevelEducation highestLevelEducation;

    @OneToMany(mappedBy = "recruitPost", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RecruitHasBenefit> benefits;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(name = "employment_type", nullable = false, length = 45)
    private String employmentType;

    @Column(nullable = false, length = 100)
    private String location;

    @Column(name = "office_hour", length = 45)
    private String officeHour;

    @Column(name = "likes_count")
    private Integer likesCount;

    @Column(name = "progress_state", nullable = false, length = 45)
    private String progressState;

    @Column(name = "content_image_url", length = 100)
    private String contentImageUrl;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "ended_at")
    private LocalDateTime endedAt;


    public RecruitPost(Long recruitPostId) {
        this.id = recruitPostId;
    }

    public void updateInfo(RecruitPostRequestDTO dto) {
        this.title = dto.title();
        this.employmentType = dto.employmentType();
        this.location = dto.location();
        this.officeHour = dto.officeHour();
        this.contentImageUrl = dto.contentImageUrl();
        this.region = new Region(dto.regionId());
        this.position = new Position(dto.positionId());
        this.positionCategory = new PositionCategory(dto.positionCategoryId());
        this.highestLevelEducation = new HighestLevelEducation(dto.highestLevelEducationId());
    }
}
