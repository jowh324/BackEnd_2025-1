package com.example.bcsd.Dto;

import jakarta.validation.constraints.NotNull;

public record MemberUpdate (
        @NotNull(message = "name cannot be null")
        String name,
        @NotNull(message = "email cannot be null")
        String email,
        @NotNull(message = "email cannot be null")
        String password
)
        {
    public static MemberUpdate of(String name,String email, String password){
        return new MemberUpdate(name,email,password);
    }


}
