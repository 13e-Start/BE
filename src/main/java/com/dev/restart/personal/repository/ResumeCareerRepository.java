package com.dev.restart.personal.repository;

import com.dev.restart.personal.entity.Resume;
import com.dev.restart.personal.entity.ResumeCareer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeCareerRepository extends JpaRepository<ResumeCareer, Long> {
    void deleteAllByResume(Resume resume);
}
