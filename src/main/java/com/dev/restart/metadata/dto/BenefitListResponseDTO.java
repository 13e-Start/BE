package com.dev.restart.metadata.dto;

import com.dev.restart.metadata.entity.EmployeeBenefit;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BenefitListResponseDTO {
    private List<EmployeeBenefit> list;
}