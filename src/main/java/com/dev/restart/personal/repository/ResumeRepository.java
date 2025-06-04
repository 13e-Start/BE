package com.dev.restart.personal.repository;

import com.dev.restart.personal.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}
