package com.dev.restart.company.controller;

import com.dev.restart.company.dto.request.FranchisePostRequestDTO;
import com.dev.restart.company.dto.response.FranchisePostDetailResponseDTO;
import com.dev.restart.company.servie.FranchisePostService;
import com.dev.restart.global.token.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/company/franchise")
@Tag(name = "Franchise API", description = "Company Franchise 관련 API 입니다.")
public class FranchisePostController {

    private final JwtTokenProvider jwtTokenProvider;
    private final FranchisePostService franchisePostService;

    @PostMapping
    @Tag(name = "Franchise API")
    @Operation(summary = "Franchise 생성", description = "Franchise 를 생성합니다.")
    public ResponseEntity<Void> createFranchise(@RequestBody FranchisePostRequestDTO dto, HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        franchisePostService.createFranchisePost(jwtTokenProvider.getCompanyId(token), dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Tag(name = "Franchise API")
    @Operation(summary = "Franchise 정보 조회", description = "Franchise 정보를 조회합니다.")
    public ResponseEntity<FranchisePostDetailResponseDTO> getFranchise(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        FranchisePostDetailResponseDTO dto = franchisePostService.getFranchisePostInfoById(jwtTokenProvider.getCompanyId(token));
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    @Tag(name = "Franchise API")
    @Operation(summary = "Franchise 업데이트", description = "Franchise 정보를 업데이트합니다.")
    public ResponseEntity<Void> updateFranchise(HttpServletRequest request,
                                                @RequestBody FranchisePostRequestDTO dto) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        franchisePostService.updateFranchisePost(jwtTokenProvider.getCompanyId(token), dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @Tag(name = "Franchise API")
    @Operation(summary = "Franchise 삭제", description = "Franchise 정보를 삭제합니다.")
    public ResponseEntity<Void> deleteFranchise(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        franchisePostService.deleteFranchisePost(jwtTokenProvider.getCompanyId(token));
        return ResponseEntity.noContent().build();
    }
}
