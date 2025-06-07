package com.dev.restart.metadata.servie;

import com.dev.restart.metadata.dto.*;
import com.dev.restart.metadata.entity.*;
import com.dev.restart.metadata.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MetaDataService {

    private final EmployeeBenefitsCategoryRepository employeeBenefitsCategoryRepository;
    private final EmployeeBenefitRepository employeeBenefitRepository;
    private final HighestLevelEducationRepository highestLevelEducationRepository;
    private final PositionCategoryRepository positionCategoryRepository;
    private final PositionRepository positionRepository;
    private final RegionRepository regionRepository;

    public BenefitCategoryListResponseDTO getBenefitCategoryList(){
        return new BenefitCategoryListResponseDTO(employeeBenefitsCategoryRepository.findAll());
    }

    public EmployeeBenefitsCategory getBenefitCategoryById(Long id){
        return employeeBenefitsCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("EmployeeBenefitsCategory not found"));
    }

    public BenefitListResponseDTO getBenefitList(){
        return new BenefitListResponseDTO(employeeBenefitRepository.findAll());
    }

    public EmployeeBenefit getBenefitById(Long id){
        return employeeBenefitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("EmployeeBenefit not found"));
    }

    public EducationListResponseDTO getEducationList(){
        return new EducationListResponseDTO(highestLevelEducationRepository.findAll());
    }

    public HighestLevelEducation getEducationById(Long id){
        return highestLevelEducationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("HighestLevelEducation not found"));
    }

    public PositionCategoryListResponseDTO getPositionCategoryList(){
        return new PositionCategoryListResponseDTO(positionCategoryRepository.findAll());
    }

    public PositionCategory getPositionCategoryById(Long id){
        return positionCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PositionCategory not found"));
    }

    public PositionListResponseDTO getPositionList(){
        return new PositionListResponseDTO(positionRepository.findAll());
    }

    public Position getPositionById(Long id){
        return positionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Position not found"));
    }

    public RegionListResponseDTO getRegionList(){
        return new RegionListResponseDTO(regionRepository.findAll());
    }

    public Region getRegionById(Long id){
        return regionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Region not found"));
    }
}
