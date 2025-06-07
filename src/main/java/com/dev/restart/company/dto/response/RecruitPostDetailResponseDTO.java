package com.dev.restart.company.dto.response;

import java.util.List;

public record RecruitPostDetailResponseDTO(
        Long id,
        String title,
        String employmentType,
        String location,
        String officeHour,
        String contentImageUrl,
        List<EmployeeBenefitDTO> benefits
) {}
