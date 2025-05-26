package com.example.bcsd.Dto;

import jakarta.validation.constraints.NotNull;

public record ArticleUpdate (
        @NotNull(message = "title cannot be null")
        String title,
        @NotNull(message = "content cannot be null")
        String content){
    public static ArticleUpdate of(String title,String content){
        return new ArticleUpdate(title,content);
    }


}
