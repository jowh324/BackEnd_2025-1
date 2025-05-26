package com.example.bcsd.Dto;

import java.time.LocalDateTime;

import com.example.bcsd.Model.Article;

public record ArticleResponse(
        Long id,
        Long board_id,
        Long author_id,
        String title,
        String content,
        LocalDateTime createdDate,
        LocalDateTime modifiedDate
) {


    public static ArticleResponse of(
            Long id, Long board_id,
            Long author_id,
            String title,
            String content,
            LocalDateTime createdDate,
            LocalDateTime modifiedDate
    ) {
        return new ArticleResponse(id, board_id, author_id, title, content, createdDate, modifiedDate);
    }


}

