package com.dev.restart.company.entity;

import com.dev.restart.metadata.entity.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "recruit_post")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitPost extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_category_id", nullable = false)
    private PositionCategory positionCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "highest_level_education_id", nullable = false)
    private HighestLevelEducation highestLevelEducation;

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

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    @Column(name = "progress_state", nullable = false, length = 45)
    private String progressState;

    @Column(name = "content_image_url", length = 100)
    private String contentImageUrl;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}
