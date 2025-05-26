package com.example.bcsd.Dao;

import com.example.bcsd.Model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleDao {
    List<Article> findByBoardId(long board_id);
    List<Article> findByAuthorId(long author_id);
    Optional<Article> findById(long id);

    Article insert(Article article);

    int update(Article article);

    boolean delete(long id);
}
