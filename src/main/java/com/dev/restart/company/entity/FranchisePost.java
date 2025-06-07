package com.dev.restart.company.entity;

import com.dev.restart.company.dto.request.FranchisePostRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "franchise_post")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FranchisePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "franchisePost", fetch = FetchType.LAZY)
    private Company company;

    @Column(length = 100)
    private String title;

    @Column(length = 45)
    private String subscribe;

    @Column(name = "initial_cost", length = 45)
    private String initialCost;

    @Column(name = "content_image_url", length = 100)
    private String contentImageUrl;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    public void update(FranchisePostRequestDTO dto) {
        this.title = dto.title();
        this.subscribe = dto.subscribe();
        this.initialCost = dto.initialCost();
        this.contentImageUrl = dto.contentImageUrl();
    }
}
