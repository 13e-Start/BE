package com.dev.restart.personal.dto.response;

import com.dev.restart.personal.entity.Resume;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class ResumePreviewDTO {
    private Long resumeId;
    private String title;
    private String lastUpdatedAt;

    public static ResumePreviewDTO from(Resume resume) {
        return ResumePreviewDTO.builder()
                .resumeId(resume.getId())
                .title(resume.getTitle())
                .lastUpdatedAt(
                        resume.getUpdatedAt() != null
                                ? resume.getUpdatedAt().toString()
                                : null
                )
                .build();
    }
}
