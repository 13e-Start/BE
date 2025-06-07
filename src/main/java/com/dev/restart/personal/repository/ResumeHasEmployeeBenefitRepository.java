package com.dev.restart.personal.repository;

import com.dev.restart.personal.entity.Resume;
import com.dev.restart.personal.entity.ResumeHasEmployeeBenefits.ResumeHasEmployeeBenefit;
import com.dev.restart.personal.entity.ResumeHasEmployeeBenefits.ResumeHasEmployeeBenefitId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeHasEmployeeBenefitRepository extends JpaRepository<ResumeHasEmployeeBenefit, ResumeHasEmployeeBenefitId> {
    void deleteAllByResume(Resume resume);
}
