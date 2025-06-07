package com.dev.restart.personal.controller;

import com.dev.restart.global.token.JwtTokenProvider;
import com.dev.restart.personal.dto.request.UpdateResumeRequestDTO;
import com.dev.restart.personal.dto.request.UploadResumeRequestDTO;
import com.dev.restart.personal.dto.response.ResumeDetailDTO;
import com.dev.restart.personal.dto.response.ResumePreviewDTO;
import com.dev.restart.personal.dto.response.ResumeStatusDTO;
import com.dev.restart.personal.dto.response.SubmittedResumeResponseDTO;
import com.dev.restart.personal.servie.ResumeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/resumes")
@Tag(name = "Resume API", description = "메타 데이터 API 입니다.")
public class ResumeController {

    private final ResumeService resumeService;
    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping
    @Tag(name = "Resume API")
    @Operation(summary = "Resume 업로드", description = "Resume를 업로드 합니다.")
    public ResponseEntity<Void> uploadResume(@RequestBody UploadResumeRequestDTO dto,
                                             HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if(token == null || !jwtTokenProvider.validateToken(token)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        resumeService.createResume(jwtTokenProvider.getUserId(token), dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    @Tag(name = "Resume API")
    @Operation(summary = "Resume 업데이트", description = "Resume를 업데이트합니다.")
    public ResponseEntity<Void> updateResume(@RequestBody UpdateResumeRequestDTO dto,
                                             HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if(token == null || !jwtTokenProvider.validateToken(token)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        resumeService.updateResume(jwtTokenProvider.getUserId(token), dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{resumeId}/submit")
    @Tag(name = "Resume API")
    @Operation(summary = "Resume 제출", description = "Resume를 제출 합니다.")
    public ResponseEntity<Void> submitResume(@PathVariable Long resumeId,
                                             @RequestParam Long recruitPostId,
                                             HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if(token == null || !jwtTokenProvider.validateToken(token)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        resumeService.submitResume(jwtTokenProvider.getUserId(token), resumeId, recruitPostId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/submitted")
    @Operation(summary = "제출한 이력서 목록 조회", description = "내가 제출한 이력서 목록을 조회합니다.")
    public ResponseEntity<List<SubmittedResumeResponseDTO>> getSubmittedResumes(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(resumeService.getSubmittedResumes(jwtTokenProvider.getUserId(token)));
    }

    @GetMapping("/my")
    @Operation(summary = "작성한 이력서 목록 조회", description = "내가 작성한 모든 이력서를 조회합니다.")
    public ResponseEntity<List<ResumePreviewDTO>> getMyResumes(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(resumeService.getMyResumes(jwtTokenProvider.getUserId(token)));
    }

    @GetMapping("/{resumeId}")
    @Operation(summary = "이력서 상세 조회", description = "이력서 상세 정보를 조회합니다.")
    public ResponseEntity<ResumeDetailDTO> getResumeDetail(@PathVariable Long resumeId, HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(resumeService.getResumeDetail(resumeId, jwtTokenProvider.getUserId(token)));
    }

    @DeleteMapping("/{resumeId}")
    @Operation(summary = "이력서 삭제", description = "제출하지 않은 이력서를 삭제합니다.")
    public ResponseEntity<Void> deleteResume(@PathVariable Long resumeId, HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        resumeService.deleteResume(resumeId, jwtTokenProvider.getUserId(token));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{resumeSnapshotId}/status")
    @Operation(summary = "이력서 지원 상태 확인", description = "이력서의 진행 상태와 최종 결과를 조회합니다.")
    public ResponseEntity<ResumeStatusDTO> getResumeStatus(@PathVariable Long resumeSnapshotId, HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(resumeService.getResumeStatus(resumeSnapshotId, jwtTokenProvider.getUserId(token)));
    }
}
