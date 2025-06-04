package com.dev.restart.company.entity;

import com.dev.restart.metadata.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company")
@Getter
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

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "tel_number", nullable = false, length = 45)
    private String telNumber;

    private Integer employeeCount;

    private Integer foundedYear;

    @Column(name = "company_type", length = 45)
    private String companyType;

    @Column(name = "symbol_image_url", length = 100)
    private String symbolImageUrl;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}
