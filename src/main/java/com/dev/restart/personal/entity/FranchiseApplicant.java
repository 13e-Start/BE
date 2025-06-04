package com.dev.restart.personal.entity;

import com.dev.restart.company.entity.FranchisePost;
import com.dev.restart.metadata.entity.Region;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "franchise_applicant")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FranchiseApplicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "franchise_post_id", nullable = false)
    private FranchisePost franchisePost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @Column(name = "initial_cost", nullable = false, length = 45)
    private String initialCost;

    @Column(nullable = false, length = 200)
    private String content;

    @Column(name = "progress_state", nullable = false, length = 45)
    private String progressState;

    @Column(name = "final_result", nullable = false, length = 45)
    private String finalResult;

    @Column(name = "submitted_at", nullable = false)
    private LocalDateTime submittedAt;
}
