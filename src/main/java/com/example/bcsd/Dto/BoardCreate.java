package com.example.bcsd.Dto;

public record BoardCreate( String name) {
    public static BoardCreate of( String name) {
        return new BoardCreate( name);
    }
}
