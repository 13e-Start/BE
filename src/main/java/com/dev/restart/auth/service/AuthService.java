package com.dev.restart.auth.service;

import com.dev.restart.auth.dto.LoginRequestDto;
import com.dev.restart.auth.dto.LoginResponseDto;
import com.dev.restart.auth.dto.SignupRequestDto;
import com.dev.restart.auth.enums.Role;
import com.dev.restart.global.error.CustomException;
import com.dev.restart.global.error.ErrorCode;
import com.dev.restart.global.token.JwtTokenProvider;
import com.dev.restart.global.token.RefreshTokenStore;
import com.dev.restart.personal.entity.User;
import com.dev.restart.personal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenStore refreshTokenStore;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequestDto request){
        // 1. 사용자 아이디 중복 체크
        if(userRepository.existsByUsername(request.getUsername())){
            throw new CustomException(ErrorCode.ALREADY_EXISTED_USERNAME);
        }

        // 2. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .username(request.getUsername())
                .password(encodedPassword)
                .name(request.getName())
                .birthDate(LocalDate.parse(request.getBirthDate()))
                .telNumber(request.getTelNumber())
                .address(request.getAddress())
                .profileImageUrl(request.getProfileImageUrl())
                .isActive(true)
                .build();

        userRepository.save(user);
    }


    public LoginResponseDto login(LoginRequestDto request){
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        // TODO: Role 를 DB에 저장할지 고민
        String accessToken = jwtTokenProvider.generateAccessToken(user.getId().toString(), Role.USER.name());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId().toString(), Role.USER.name());

        refreshTokenStore.save(user.getId().toString(), refreshToken);

        return new LoginResponseDto(accessToken, refreshToken);
    }

    public void logout(String token){
        refreshTokenStore.remove(jwtTokenProvider.getUserId(token));
    }

    public LoginResponseDto reissue(String bearerRefreshToken){
        String refreshToken = bearerRefreshToken.replace("Bearer", "").trim();
        String userId = jwtTokenProvider.getUserId(refreshToken);

        String savedToken = refreshTokenStore.get(userId);

        if(savedToken == null){
            throw new CustomException(ErrorCode.ALREADY_LOGOUT);
        }
        if(!refreshToken.equals(savedToken)){
            throw new CustomException(ErrorCode.INVALID_REFRESHTOKEN);
        }
        refreshTokenStore.remove(userId);

        String newAccessToken = jwtTokenProvider.generateAccessToken(userId, Role.USER.name());
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(userId, Role.USER.name());
        refreshTokenStore.save(userId, newRefreshToken);

        return new LoginResponseDto(newAccessToken, refreshToken);
    }

    public boolean isExistUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
