package com.dev.restart.company.dto.request;

import java.util.List;

public record RecruitPostRequestDTO(
        String title,
        String employmentType,
        String location,
        String officeHour,
        String contentImageUrl,
        Long regionId,
        Long positionId,
        Long positionCategoryId,
        Long highestLevelEducationId,
        List<Long> benefitIds
) {}