package com.dev.restart.auth.service;

import com.dev.restart.auth.dto.CompanySignupRequestDTO;
import com.dev.restart.auth.dto.LoginRequestDto;
import com.dev.restart.auth.dto.LoginResponseDto;
import com.dev.restart.auth.dto.SignupRequestDto;
import com.dev.restart.auth.enums.Role;
import com.dev.restart.company.entity.Company;
import com.dev.restart.company.entity.FranchisePost;
import com.dev.restart.company.repository.CompanyRepository;
import com.dev.restart.company.repository.FranchisePostRepository;
import com.dev.restart.company.servie.FranchisePostService;
import com.dev.restart.global.error.CustomException;
import com.dev.restart.global.error.ErrorCode;
import com.dev.restart.global.token.JwtTokenProvider;
import com.dev.restart.global.token.RefreshTokenStore;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyAuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final CompanyRepository companyRepository;
    private final RefreshTokenStore refreshTokenStore;
    private final PasswordEncoder passwordEncoder;

    private final FranchisePostRepository franchisePostRepository;

    public void signup(CompanySignupRequestDTO request) {
        if (companyRepository.existsByUsername(request.getUsername())) {
            throw new CustomException(ErrorCode.ALREADY_EXISTED_USERNAME);
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        FranchisePost franchisePost = franchisePostRepository.save(
                FranchisePost.builder().build()
        );

        Company company = Company.builder()
                .id(UUID.randomUUID().toString())
                .username(request.getUsername())
                .password(encodedPassword)
                .name(request.getName())
                .businessNumber(request.getBusinessNumber())
                .telNumber(request.getTelNumber())
                .employeeCount(request.getEmployeeCount())
                .foundedYear(request.getFoundedYear())
                .companyType(request.getCompanyType())
                .symbolImageUrl(request.getSymbolImageUrl())
                .franchisePost(franchisePost)
                .build();

        companyRepository.save(company);
    }

    public LoginResponseDto login(LoginRequestDto request) {
        Company company = companyRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), company.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        String accessToken = jwtTokenProvider.generateAccessToken(company.getId(), Role.COMPANY.name());
        String refreshToken = jwtTokenProvider.generateRefreshToken(company.getId(), Role.COMPANY.name());

        refreshTokenStore.save(company.getId(), refreshToken);

        return new LoginResponseDto(accessToken, refreshToken);
    }

    public void logout(String token) {
        refreshTokenStore.remove(jwtTokenProvider.getUserId(token));
    }

    public LoginResponseDto reissue(String bearerRefreshToken) {
        String refreshToken = bearerRefreshToken.replace("Bearer", "").trim();
        String userId = jwtTokenProvider.getUserId(refreshToken);

        String savedToken = refreshTokenStore.get(userId);

        if (savedToken == null) {
            throw new CustomException(ErrorCode.ALREADY_LOGOUT);
        }
        if (!refreshToken.equals(savedToken)) {
            throw new CustomException(ErrorCode.INVALID_REFRESHTOKEN);
        }

        refreshTokenStore.remove(userId);

        String newAccessToken = jwtTokenProvider.generateAccessToken(userId, Role.COMPANY.name());
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(userId, Role.COMPANY.name());

        refreshTokenStore.save(userId, newRefreshToken);

        return new LoginResponseDto(newAccessToken, newRefreshToken);
    }

    public boolean isExistUsername(String username) {
        return companyRepository.existsByUsername(username);
    }
}