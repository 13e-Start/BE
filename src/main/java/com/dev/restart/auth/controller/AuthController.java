package com.dev.restart.auth.controller;

import com.dev.restart.auth.dto.LoginRequestDto;
import com.dev.restart.auth.dto.LoginResponseDto;
import com.dev.restart.auth.dto.SignupRequestDto;
import com.dev.restart.auth.service.AuthService;
import com.dev.restart.global.error.CustomException;
import com.dev.restart.global.error.ErrorCode;
import com.dev.restart.global.token.JwtTokenProvider;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Auth API", description = "authentication 관련 API 입니다.")
public class AuthController {

    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequestDto request){
        authService.signup(request);
        return ResponseEntity.ok("회원가입 성공");
    }

    @GetMapping("/signup/{username}")
    public ResponseEntity<?> isExistedUsername(@PathVariable("username") String username){
        if(authService.isExistUsername(username)) throw new CustomException(ErrorCode.ALREADY_EXISTED_USERNAME);
        return ResponseEntity.ok("사용 가능한 아이디입니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request);
        if(token == null || !jwtTokenProvider.validateToken(token)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        log.info(token);
        authService.logout(token);
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@CookieValue("RefreshToken") String bearerRefreshToken){
        return ResponseEntity.ok(authService.reissue(bearerRefreshToken));
    }
}
