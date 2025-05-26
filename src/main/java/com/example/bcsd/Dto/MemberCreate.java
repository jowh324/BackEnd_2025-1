package com.example.bcsd.Dto;

import jakarta.validation.constraints.NotNull;

public record MemberCreate (

        @NotNull(message="name cannot be null")
        String name,
        @NotNull(message="email cannot be null")
        String email,
        @NotNull(message="password cannot be null")
        String password) {
    public MemberCreate of(String name, String email, String password) {
        return new MemberCreate(name, email, password);
    }
}
