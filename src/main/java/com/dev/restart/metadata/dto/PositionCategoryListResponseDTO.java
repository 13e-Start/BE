package com.dev.restart.metadata.dto;

import com.dev.restart.metadata.entity.PositionCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PositionCategoryListResponseDTO {
    private List<PositionCategory> list;
}
