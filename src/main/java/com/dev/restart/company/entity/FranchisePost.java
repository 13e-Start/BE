package com.dev.restart.company.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "franchise_post")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FranchisePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 45)
    private String subscribe;

    @Column(name = "initial_cost", nullable = false, length = 45)
    private String initialCost;

    @Column(name = "content_image_url", length = 100)
    private String contentImageUrl;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}
