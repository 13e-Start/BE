package com.dev.restart.personal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resume_career")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeCareer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id", nullable = false)
    private Resume resume;

    @Column(name = "company_name", nullable = false, length = 100)
    private String companyName;

    @Column(name = "employment_period", nullable = false, length = 45)
    private String employmentPeriod;

    @Column(name = "job_position", nullable = false, length = 45)
    private String jobPosition;
}
