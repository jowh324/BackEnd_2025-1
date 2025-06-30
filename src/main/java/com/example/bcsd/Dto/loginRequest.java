package com.example.bcsd.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record loginRequest(
        @NotNull(message="로그인 이메일이 비어있습니다.")
        String email,
        @NotNull(message="로그인 비밀번호이가 비어있습니다.")
        String password) {

    public static  loginRequest of(String email, String password) {
        return new loginRequest(email, password);
    }
}
