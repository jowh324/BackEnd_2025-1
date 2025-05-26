package com.example.bcsd.Dto;

import java.time.LocalDateTime;

public record MemberResponse(
        Long id,
        String name,
        String email,
        String password
) {


    public static MemberResponse of(
            Long id, String name, String email, String password
    ) {
        return new MemberResponse(id, name, email, password);
    }


}
