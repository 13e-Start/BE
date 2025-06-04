package com.dev.restart.personal.entity;

import com.dev.restart.company.entity.RecruitPost;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "recruit_applicant")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitApplicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_snapshot_id", nullable = false)
    private ResumeSnapshot resumeSnapshot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_post_id", nullable = false)
    private RecruitPost recruitPost;

    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;

    @Column(name = "progress_status", nullable = false, length = 45)
    private String progressStatus;

    @Column(name = "final_result", nullable = false, length = 45)
    private String finalResult;
}
