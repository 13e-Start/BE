package com.dev.restart.company.repository;

import com.dev.restart.company.entity.RecruitPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitHasBenefitRepository extends JpaRepository<RecruitPost, Long> {
}
