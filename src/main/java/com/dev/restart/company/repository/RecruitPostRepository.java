package com.dev.restart.company.repository;

import com.dev.restart.company.entity.Company;
import com.dev.restart.company.entity.RecruitPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecruitPostRepository extends JpaRepository<RecruitPost, Long> {

    List<RecruitPost> findAllByCompany(Company company);

    @Query("""
        SELECT DISTINCT rp FROM RecruitPost rp
        LEFT JOIN rp.region r
        LEFT JOIN rp.position p
        LEFT JOIN rp.positionCategory pc
        LEFT JOIN rp.highestLevelEducation hle
        LEFT JOIN rp.benefits rhb
        WHERE rp.isActive = true
        AND (:regionIds IS NULL OR r.id IN :regionIds)
        AND (:positionIds IS NULL OR p.id IN :positionIds)
        AND (:positionCategoryIds IS NULL OR pc.id IN :positionCategoryIds)
        AND (:educationIds IS NULL OR hle.id IN :educationIds)
        AND (:benefitIds IS NULL OR rhb.employeeBenefit.id IN :benefitIds)
    """)
    List<RecruitPost> searchRecruitPostsWithFilter(
            @Param("regionIds") List<Long> regionIds,
            @Param("positionIds") List<Long> positionIds,
            @Param("positionCategoryIds") List<Long> positionCategoryIds,
            @Param("educationIds") List<Long> educationIds,
            @Param("benefitIds") List<Long> benefitIds
    );
}
