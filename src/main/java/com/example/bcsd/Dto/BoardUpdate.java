package com.example.bcsd.Dto;

public record BoardUpdate (String name){
    public BoardUpdate of(String name){
        return new BoardUpdate(name);
    }
}
