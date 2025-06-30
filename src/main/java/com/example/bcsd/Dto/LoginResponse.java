package com.example.bcsd.Dto;

public record LoginResponse( String email) {
    public static LoginResponse of(String email) {
        return new LoginResponse( email);
    }
}

