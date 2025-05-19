package com.example.bcsd.Service;

import com.example.bcsd.Dao.ArticleDao;
import com.example.bcsd.Model.Article;
import com.example.bcsd.repository.ArticleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;


    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getArticlesByBoardId(Long board_id) {
        return articleRepository.findByBoardId(board_id);
    }
    public ResponseEntity<Article> getArticleById(Long id) {
        Article article = articleRepository.findById(id);
        if (article == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(article);
    }
    public ResponseEntity<Article> createArticle( Article article) {
        articleRepository.insert(article);
        return ResponseEntity.ok(article);
    }
    public ResponseEntity<Article> updateArticle(Article article, Long id) {
        Article articles = articleRepository.findById(id);
        if (articles == null) {
            return ResponseEntity.notFound().build();
        }
        articles.setTitle((String) article.getTitle());
        articles.setContent((String) article.getContent());
        articles.setModified_date(LocalDateTime.now());
        articleRepository.update(articles);
        // 변경된 정보를 다시 조회해서 돌려줄 수도 있습니다.
        return ResponseEntity.ok(articleRepository.findById(id));
    }
    public void deleteArticle(Long id) {
        if(!articleRepository.delete(id)) {
            throw new NullPointerException("해당 id가 존재하지 않습니다.");
        }
    }


}
