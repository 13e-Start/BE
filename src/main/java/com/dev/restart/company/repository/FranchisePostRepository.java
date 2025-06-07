package com.dev.restart.company.repository;

import com.dev.restart.company.entity.Company;
import com.dev.restart.company.entity.FranchisePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FranchisePostRepository extends JpaRepository<FranchisePost, Long> {

    Optional<FranchisePost> findByCompany(Company company);

    @Query("""
        SELECT fp FROM FranchisePost fp
        LEFT JOIN fp.company c
        WHERE fp.isActive = true
        AND (:companyIds IS NULL OR c.id IN :companyIds)
        AND (:subscribe IS NULL OR fp.subscribe = :subscribe)
        AND (:maxInitialCost IS NULL OR CAST(fp.initialCost AS integer) <= :maxInitialCost)
    """)
    List<FranchisePost> searchFranchisePostsWithFilter(
            @Param("companyIds") List<Long> companyIds,
            @Param("subscribe") String subscribe,
            @Param("maxInitialCost") Integer maxInitialCost
    );
}
