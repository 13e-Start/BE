package com.dev.restart.metadata.dto;

import com.dev.restart.metadata.entity.HighestLevelEducation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class EducationListResponseDTO {
    private List<HighestLevelEducation> list;
}
