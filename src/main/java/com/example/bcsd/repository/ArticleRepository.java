package com.example.bcsd.repository;

import com.example.bcsd.Model.Article;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ArticleRepository {
    private final AtomicLong counter = new AtomicLong(1);
    private Map<Long, Article> storage = new HashMap<>();

    public List<Article> findAll() {
        List<Article> articles = new ArrayList<>();
        for (Article article : storage.values()) {
            articles.add(article);
        }
        return articles;
    }


    public Article getArticle(Long id) {

        return storage.get(id);
    }

    public Article createArticle(Article article) {
        article.setId(counter.getAndIncrement());
        if (article.getDate() == null || article.getDate().isBlank()) {
            article.setDate(LocalDate.now().toString());
        }
        storage.put(article.getId(), article);
        return article;
    }

    public boolean updateArticle(Long id, Article updatedArticle) {
        Article article = storage.get(id);
        if (article == null) {
            return false;
        }
        article.update(updatedArticle.getTitle(), updatedArticle.getContent(), updatedArticle.getDate());


        return true;
    }


    public boolean delete(Long id) {
        Article remove = storage.remove(id);
        return remove != null;
    }
}
