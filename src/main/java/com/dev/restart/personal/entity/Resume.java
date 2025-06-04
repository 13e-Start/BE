package com.dev.restart.personal.entity;

import com.dev.restart.metadata.entity.BaseEntity;
import com.dev.restart.metadata.entity.HighestLevelEducation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "highest_level_education_id", nullable = false)
    private HighestLevelEducation highestLevelEducation;

    @Column(nullable = false)
    private Integer version;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 100)
    private String education;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}
