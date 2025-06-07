package com.dev.restart.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Auth 관련 에러코드
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "ACCOUNT-001", "사용자를 찾을 수 없습니다."),
    ALREADY_EXISTED_USERNAME(HttpStatus.BAD_REQUEST, "ACCOUNT-002", "존재하는 계정입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "ACCOUNT-003", "비밀번호가 일치하지 않습니다."),
    ALREADY_LOGOUT(HttpStatus.BAD_REQUEST, "ACCOUNT-004", "로그아웃된 사용자입니다."),
    INVALID_REFRESHTOKEN(HttpStatus.BAD_REQUEST, "ACCOUNT-005", "유효하지않은 토큰입니다.");

    private final HttpStatus httpStatus; // HttpStatus
    private final String code;           // ACCOUNT-001
    private final String message;        // 설명
}
