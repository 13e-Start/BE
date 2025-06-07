package com.dev.restart.personal.controller;

import com.dev.restart.global.token.JwtTokenProvider;
import com.dev.restart.personal.dto.response.UserResponseDTO;
import com.dev.restart.personal.servie.UserService;
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
@RequestMapping("/api/v1/user")
@Tag(name = "User API", description = "사용자 관련 API 입니다.")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    @Tag(name = "User API")
    @Operation(summary = "User 정보 조회", description = "User 정보를 조회합니다.")
    public ResponseEntity<UserResponseDTO> getUserByToken(HttpServletRequest request){
        String token = jwtTokenProvider.resolveToken(request);
        if(token == null || !jwtTokenProvider.validateToken(token)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(userService.getUserInfo(jwtTokenProvider.getUserId(token)));
    }
}
