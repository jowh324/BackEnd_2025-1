package com.example.bcsd;

import com.example.bcsd.Model.Article;
import com.example.bcsd.repository.ArticleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping
    public List<Article> listArticles() {
        return articleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        Article article = articleRepository.getArticle(id);
        if (article != null) {
            return ResponseEntity.ok(article);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article post = articleRepository.createArticle(article);
        return ResponseEntity.ok(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        boolean article1 = articleRepository.updateArticle(id, article);
        if (!article1) {
            return ResponseEntity.notFound().build();
        }
        Article article2 = articleRepository.getArticle(id);
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        boolean remove = articleRepository.delete(id);
        if (remove) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}