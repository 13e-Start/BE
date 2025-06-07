package com.dev.restart.personal.repository;

import com.dev.restart.personal.entity.Resume;
import com.dev.restart.personal.entity.ResumeHasRegions.ResumeHasRegion;
import com.dev.restart.personal.entity.ResumeHasRegions.ResumeHasRegionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeHasRegionRepository extends JpaRepository<ResumeHasRegion, ResumeHasRegionId> {
    void deleteAllByResume(Resume resume);
}
