package com.dev.restart.company.controller;

import com.dev.restart.company.dto.request.RecruitPostRequestDTO;
import com.dev.restart.company.dto.response.RecruitPostDetailResponseDTO;
import com.dev.restart.company.dto.response.RecruitPostPreviewDTO;
import com.dev.restart.company.servie.RecruitPostService;
import com.dev.restart.global.token.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/company/recruit")
@Tag(name = "Recruit API", description = "Company Recruit 관련 API 입니다.")
public class RecruitPostController {

    private final JwtTokenProvider jwtTokenProvider;
    private final RecruitPostService recruitPostService;


    // Recruit 등록
    @PostMapping
    @Tag(name = "Recruit API")
    @Operation(summary = "Recruit Post 생성", description = "Recruit Post를 생성합니다.")
    public ResponseEntity<Void> createRecruit(@RequestBody RecruitPostRequestDTO dto, HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        recruitPostService.createRecruitPost(jwtTokenProvider.getCompanyId(token), dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{postId}")
    @Tag(name = "Recruit API")
    @Operation(summary = "Recruit Post 생성", description = "Recruit Post를 조회합니다.")
    public ResponseEntity<RecruitPostDetailResponseDTO> getRecruitPost(@PathVariable Long postId) {
        return ResponseEntity.ok(recruitPostService.getRecruitPostInfoById(postId));
    }

    @PutMapping("/{postId}")
    @Tag(name = "Recruit API")
    @Operation(summary = "Recruit Post 생성", description = "Recruit Post를 업데이트합니다.")
    public ResponseEntity<Void> updateRecruitPost(@PathVariable Long postId,
                                                  @RequestBody @Valid RecruitPostRequestDTO dto) {
        recruitPostService.updateRecruitPost(postId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    @Tag(name = "Recruit API")
    @Operation(summary = "Recruit Post 생성", description = "Recruit Post를 삭제합니다.")
    public ResponseEntity<Void> deleteRecruitPost(@PathVariable Long postId) {
        recruitPostService.deleteRecruitPost(postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Tag(name = "Recruit API")
    @Operation(summary = "Recruit Post List 조회", description = "Recruit Post List를 조회합니다.")
    public ResponseEntity<List<RecruitPostDetailResponseDTO>> getAllRecruitPosts(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(recruitPostService.getAllRecruitPosts(jwtTokenProvider.getCompanyId(token)));
    }
}
