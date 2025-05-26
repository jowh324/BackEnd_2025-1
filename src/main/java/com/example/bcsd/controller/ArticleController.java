package com.example.bcsd.controller;

import com.example.bcsd.Dto.ArticleCreate;
import com.example.bcsd.Dto.ArticleResponse;

import com.example.bcsd.Dto.ArticleUpdate;
import com.example.bcsd.Service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private ArticleService articleService;

    public ArticleController(ArticleService articleService ) {
        this.articleService = articleService;

    }


    @GetMapping
    public List<ArticleResponse> getArticlesByBoardId(@RequestParam("boardId") Long board_id) {
        return articleService.getArticlesByBoardId(board_id);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable Long id) {
       ArticleResponse response = articleService.getArticleById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
        public ResponseEntity<ArticleResponse> createArticle(@Valid @RequestBody ArticleCreate article) throws IllegalAccessException {
        ArticleResponse response = articleService.createArticle(article);
        return ResponseEntity.created(URI.create("/" + response.id())).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable Long id, @Valid @RequestBody ArticleUpdate article) throws IllegalAccessException {
        ArticleResponse re = articleService.updateArticle(article,id);
        return ResponseEntity.ok(re);
     }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }


}