package com.example.bcsd.Dao;

import com.example.bcsd.Model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleDao {
    List<Article> findByBoardId(long board_id);
    List<Article> findByAuthorId(long author_id);
    Article findById(long id);

    Article insert(Article article);

    Article update(Article article);

    boolean delete(long id);
}
