package com.dev.restart.personal.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class SubmitResumeRequestDTO {
    private Long highestLevelEducationId;           // 학력 ID
    private List<Long> desiredEmployeeBenefitIds;   // 희망 복지 ID 리스트
    private List<Long> desiredPositionIds;          // 희망 직무 ID 리스트
    private List<Long> desiredRegionIds;            // 희망 지역 ID 리스트
    private List<UploadCareerRequestDTO> careers;   // 커리어 이력 (아래 별도 정의)
    private Integer version;                        // 버전
    private String title;                           // 제목
    private String content;                         // 내용
}