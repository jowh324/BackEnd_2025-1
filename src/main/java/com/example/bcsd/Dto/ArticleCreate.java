package com.example.bcsd.Dto;

import com.example.bcsd.Model.Article;
import com.example.bcsd.Model.Article;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ArticleCreate(
        @NotNull(message = "board_id cannot be null")
        Long board_id,
        @NotNull (message = "author_id cannot be null")
        Long author_id,
        @NotBlank (message = "title cannot be null")
        String title,
        @NotBlank (message = "content cannot be null")
        String content) {


    public ArticleCreate of(Long board_id,
                            Long author_id,
                            String title,
                            String content){
        return new ArticleCreate(board_id, author_id, title, content);
    }
}




