package com.example.bcsd.controller;

import com.example.bcsd.Dao.ArticleDao;
import com.example.bcsd.Dao.BoardDao;
import com.example.bcsd.Dao.MemberDao;
import com.example.bcsd.Model.Article;
import com.example.bcsd.Model.Board;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ArticleController {
    private ArticleDao articleDao;
    private BoardDao boardDao;

    public ArticleController(ArticleDao articleDao, BoardDao boardDao) {
        this.articleDao = articleDao;
        this.boardDao = boardDao;

    }


    @GetMapping("/articles")
    public List<Article> getArticles(@RequestParam("boardId") Long board_id) {
        return articleDao.findByBoardId(board_id);
    }


    @GetMapping("/articles/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        Article article = articleDao.findById(id);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(article);
    }

    @PostMapping("/articles")
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article post = articleDao.insert(article);
        return ResponseEntity.ok(article);
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Map<String, Object> payload) {

        Article article = articleDao.findById(id);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        article.setTitle((String) payload.get("title"));
        article.setContent((String) payload.get("content"));
        articleDao.update(article);
        // 변경된 정보를 다시 조회해서 돌려줄 수도 있습니다.
        return ResponseEntity.ok(articleDao.findById(id));
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        int row = articleDao.delete(id);
        if (row == 0) {
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }


}