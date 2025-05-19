package com.example.bcsd.Dao;

import com.example.bcsd.Model.Article;

import java.util.List;

public interface ArticleDao {
    List<Article> findByBoardId(long board_id);

    Article findById(long id);

    Article insert(Article article);

    int update(Article article);

    int delete(long id);
}
