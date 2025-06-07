package com.dev.restart.personal.repository;

import com.dev.restart.personal.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Optional<Resume> findByIdAndUserId(Long resumeId, String userId);

    List<Resume> findAllByUserId(String userId);
}
