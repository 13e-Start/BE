package com.dev.restart.metadata.dto;

import com.dev.restart.metadata.entity.EmployeeBenefitsCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BenefitCategoryListResponseDTO {
    private List<EmployeeBenefitsCategory> list;
}
