package com.dev.restart.metadata.repository;

import com.dev.restart.metadata.entity.EmployeeBenefitsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeBenefitsCategoryRepository extends JpaRepository<EmployeeBenefitsCategory, Long> {
}
