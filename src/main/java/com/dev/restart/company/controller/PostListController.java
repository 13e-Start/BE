package com.dev.restart.company.controller;

import com.dev.restart.company.dto.response.FranchisePostPreviewDTO;
import com.dev.restart.company.dto.response.RecruitPostPreviewDTO;
import com.dev.restart.company.servie.FranchisePostService;
import com.dev.restart.company.servie.RecruitPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@Tag(name = "All Post List API", description = "전체 공고 리스트 필터링 조회 API 입니다.")
public class PostListController {

    private final RecruitPostService recruitPostService;
    private final FranchisePostService franchisePostService;

    @GetMapping("/recruit/search")
    @Tag(name = "All Post List API")
    @Operation(summary = "Recruit Post List 필터링 조회", description = "Recruit Post List를 필터링한 결과를 조회합니다.")
    public ResponseEntity<List<RecruitPostPreviewDTO>> searchRecruitPosts(
            @RequestParam(required = false) List<Long> regionIds,
            @RequestParam(required = false) List<Long> positionIds,
            @RequestParam(required = false) List<Long> positionCategoryIds,
            @RequestParam(required = false) List<Long> educationIds,
            @RequestParam(required = false) List<Long> benefitIds
    ) {
        List<RecruitPostPreviewDTO> posts = recruitPostService.searchRecruitPosts(
                regionIds, positionIds, positionCategoryIds, educationIds, benefitIds);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/franchise/search")
    @Tag(name = "All Post List API")
    @Operation(summary = "Franchise Post List 필터링 조회", description = "Franchise Post List를 필터링한 결과를 조회합니다.")
    public ResponseEntity<List<FranchisePostPreviewDTO>> searchFranchisePosts(
            @RequestParam(required = false) List<Long> companyIds,
            @RequestParam(required = false) String subscribe,
            @RequestParam(required = false) Integer maxInitialCost
    ) {
        List<FranchisePostPreviewDTO> posts = franchisePostService.searchFranchisePosts(
                companyIds, subscribe, maxInitialCost);
        return ResponseEntity.ok(posts);
    }
}
