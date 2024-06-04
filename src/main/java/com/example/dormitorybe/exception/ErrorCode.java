package com.example.dormitorybe.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND.value(), "M001", "유저가 존재하지 않습니다."),
    ALREADY_HAVE_ID(HttpStatus.BAD_REQUEST.value(), "M002", "이미 존재하는 아이디 입니다."),
    NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST.value(), "M003", "비밀번호가 일치하지 않습니다."),
    UnAuthorized(HttpStatus.UNAUTHORIZED.value(), "M004", "로그인을 해주세요."),
//    DELETED_USER_EXCEPTION(HttpStatus.BAD_REQUEST.value(), "M002", "이미 탈퇴한 계정입니다.\n다른 계정으로 시도해 주세요."),
//    NOT_MATCH_MEMBER(HttpStatus.BAD_REQUEST.value(), "M003", "작성자가 일치하지 않습니다."),

    NOT_FOUND_POST(HttpStatus.NOT_FOUND.value(), "P001", "게시물을 찾을 수 없습니다."),

    ERROR(HttpStatus.NO_CONTENT.value(), "S001", "알수없는오류");


    private final int status;
    private final String code;
    private final String message;
}