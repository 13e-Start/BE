package com.dev.restart.company.entity;

import com.dev.restart.metadata.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "company")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company extends BaseEntity {

    @Id
    private String id;

    @Column(nullable = false, length = 45)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 45)
    private String name;

    @Column(nullable = false, length = 45)
    private String businessNumber;

    @Column(name = "tel_number", nullable = false, length = 45)
    private String telNumber;

    private Integer employeeCount;

    private Integer foundedYear;

    @Column(name = "company_type", length = 45)
    private String companyType;

    @Column(name = "symbol_image_url", length = 100)
    private String symbolImageUrl;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "franchise_post_id")
    private FranchisePost franchisePost;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<RecruitPost> recruitPosts = new HashSet<>();;
}
