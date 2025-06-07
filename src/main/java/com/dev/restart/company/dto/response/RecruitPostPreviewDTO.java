package com.dev.restart.company.dto.response;

import com.dev.restart.company.entity.RecruitPost;

public record RecruitPostPreviewDTO(
        Long id,
        String title,
        String companyName,
        String region,
        String position,
        String employmentType
) {
    public static RecruitPostPreviewDTO fromEntity(RecruitPost post) {
        return new RecruitPostPreviewDTO(
                post.getId(),
                post.getTitle(),
                post.getCompany().getName(),
                post.getRegion().getName(),
                post.getPosition().getName(),
                post.getEmploymentType()
        );
    }
}
