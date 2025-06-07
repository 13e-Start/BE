package com.dev.restart.metadata.controller;

import com.dev.restart.metadata.dto.*;
import com.dev.restart.metadata.servie.MetaDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meta")
@Tag(name = "MetaData API", description = "메타 데이터 API 입니다.")
public class MetaDataListController {

    private final MetaDataService metaDataService;

    @GetMapping("/BenefitCategory")
    @Tag(name = "MetaData API")
    @Operation(summary = "BenefitCategory List 조회", description = "복리후생 카테고리 List 정보를 조회합니다.")
    public ResponseEntity<BenefitCategoryListResponseDTO> getBenefitCategoryList(){
        return ResponseEntity.ok(metaDataService.getBenefitCategoryList());
    }

    @GetMapping("/Benefit")
    @Tag(name = "MetaData API")
    @Operation(summary = "Benefit List 조회", description = "복리후생 List 정보를 조회합니다.")
    public ResponseEntity<BenefitListResponseDTO> getBenefitList(){
        return ResponseEntity.ok(metaDataService.getBenefitList());
    }

    @GetMapping("/HighestLevelEducation")
    @Tag(name = "MetaData API")
    @Operation(summary = "Highest Level of Education List 조회", description = "최종학력 List 정보를 조회합니다.")
    public ResponseEntity<EducationListResponseDTO> getEducationList(){
        return ResponseEntity.ok(metaDataService.getEducationList());
    }

    @GetMapping("/PositionCategory")
    @Tag(name = "MetaData API")
    @Operation(summary = "PositionCategory List 조회", description = "직무 카테고리 List 정보를 조회합니다.")
    public ResponseEntity<PositionCategoryListResponseDTO> getPositionCategoryList(){
        return ResponseEntity.ok(metaDataService.getPositionCategoryList());
    }

    @GetMapping("/Position")
    @Tag(name = "MetaData API")
    @Operation(summary = "Position List 조회", description = "직무 List 정보를 조회합니다.")
    public ResponseEntity<PositionListResponseDTO> getPositionList(){
        return ResponseEntity.ok(metaDataService.getPositionList());
    }

    @GetMapping("/Region")
    @Tag(name = "MetaData API")
    @Operation(summary = "Region List 조회", description = "지역 List 정보를 조회합니다.")
    public ResponseEntity<RegionListResponseDTO> getRegionList(){
        return ResponseEntity.ok(metaDataService.getRegionList());
    }
}
