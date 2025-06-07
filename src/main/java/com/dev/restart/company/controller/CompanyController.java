package com.dev.restart.company.controller;

import com.dev.restart.company.dto.response.CompanyInfoResponseDTO;
import com.dev.restart.company.servie.CompanyService;
import com.dev.restart.global.token.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/company")
@Tag(name = "Company API", description = "Company 관련 API 입니다.")
public class CompanyController {

    private final CompanyService companyService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    @Tag(name = "Company API")
    @Operation(summary = "Company 정보 조회", description = "Company 정보를 조회합니다.")
    private ResponseEntity<CompanyInfoResponseDTO> getCompanyId(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null || !jwtTokenProvider.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(companyService.getCompanyInfoById(jwtTokenProvider.getCompanyId(token)));
    }
}