package com.dev.restart.auth.controller;

import com.dev.restart.auth.dto.CompanySignupRequestDTO;
import com.dev.restart.auth.dto.LoginRequestDto;
import com.dev.restart.auth.dto.LoginResponseDto;
import com.dev.restart.auth.service.CompanyAuthService;
import com.dev.restart.global.token.JwtTokenProvider;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/company")
@Tag(name = "Company Auth API", description = "Company authentication 관련 API 입니다.")
@RequiredArgsConstructor
public class CompanyAuthController {

    private final CompanyAuthService companyAuthService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody CompanySignupRequestDTO request) {
        companyAuthService.signup(request);
        return ResponseEntity.ok("기업 회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(companyAuthService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request);
        if(token == null || !jwtTokenProvider.validateToken(token)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        companyAuthService.logout(token);
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@CookieValue("RefreshToken") String bearerRefreshToken){
        return ResponseEntity.ok(companyAuthService.reissue(bearerRefreshToken));
    }
}
