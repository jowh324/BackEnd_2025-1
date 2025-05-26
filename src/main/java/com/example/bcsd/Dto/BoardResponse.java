package com.example.bcsd.Dto;

public record BoardResponse( Long id,String name) {
    public static BoardResponse of( Long id, String name) {
        return new BoardResponse(id, name);
    }
}
