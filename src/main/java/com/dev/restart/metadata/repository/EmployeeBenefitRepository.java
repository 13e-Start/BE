package com.dev.restart.metadata.repository;

import com.dev.restart.metadata.entity.EmployeeBenefit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeBenefitRepository extends JpaRepository<EmployeeBenefit, Long> {
}