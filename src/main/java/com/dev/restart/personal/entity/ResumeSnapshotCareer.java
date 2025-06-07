package com.dev.restart.personal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resume_snapshot_career")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeSnapshotCareer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_snapshot_id", nullable = false)
    private ResumeSnapshot resumeSnapshot;

    @Column(name = "company_name", nullable = false, length = 100)
    private String companyName;

    @Column(name = "job_title", nullable = false, length = 45)
    private String jobTitle;

    @Column(name = "employment_period", nullable = false, length = 45)
    private String employmentPeriod;

    @Column(name = "job_position", nullable = false, length = 45)
    private String jobPosition;

    public static ResumeSnapshotCareer of(ResumeSnapshot snapshot, ResumeCareer career) {
        return ResumeSnapshotCareer.builder()
                .resumeSnapshot(snapshot)
                .companyName(career.getCompanyName())
                .jobTitle(career.getJobTitle())
                .employmentPeriod(career.getEmploymentPeriod())
                .jobPosition(career.getJobPosition())
                .build();
    }
}
