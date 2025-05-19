package com.example.bcsd.controller;

import com.example.bcsd.Model.Article;

import com.example.bcsd.Service.ArticleService;
import com.example.bcsd.Service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ArticleController {
    private ArticleService articleService;
    private BoardService boardService;

    public ArticleController(ArticleService articleService, BoardService boardService) {
        this.articleService = articleService;
        this.boardService = boardService;

    }


    @GetMapping("/articles")
    public List<Article> getArticlesByBoardId(@RequestParam("boardId") Long board_id) {
        return articleService.getArticlesByBoardId(board_id);
    }


    @GetMapping("/articles/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

    @PostMapping("/articles")
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {

        return articleService.createArticle(article);
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        return articleService.updateArticle(article, id);
    }

    @DeleteMapping("/articles/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }


}