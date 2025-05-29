package com.example.bcsd.Service;

import com.example.bcsd.Dao.ArticleDao;
import com.example.bcsd.Dao.BoardDao;
import com.example.bcsd.Dao.MemberDao;
import com.example.bcsd.Dto.ArticleCreate;
import com.example.bcsd.Dto.ArticleResponse;
import com.example.bcsd.Dto.ArticleUpdate;
import com.example.bcsd.Model.Article;
import com.example.bcsd.Model.Board;
import com.example.bcsd.Model.Member;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private final ArticleDao articleDao;
    private final MemberDao memberDao;
    private final BoardDao boardDao;
    public ArticleService(ArticleDao articleDao, MemberDao memberDao, BoardDao boardDao) {
        this.articleDao = articleDao;
        this.memberDao = memberDao;
        this.boardDao = boardDao;

    }

    @Transactional
    public List<ArticleResponse> getArticlesByBoardId(Long board_id) {
       if(articleDao.findByBoardId(board_id)==null){
           throw new EntityNotFoundException("Article Not Found"+"(board_id:"+board_id+")");}
        return articleDao.findByBoardId(board_id).stream().map(a -> ArticleResponse.of(
                        a.getId(),
                        a.getBoard_id(),
                        a.getAuthor_id(),
                        a.getTitle(),
                        a.getContent(),
                        a.getCreated_date(),
                        a.getModified_date()
                ))
                .collect(Collectors.toList());
    }
    @Transactional
    public ArticleResponse getArticleById(Long id) {
        if(articleDao.findById(id)==null){
            throw new EntityNotFoundException("Article Not Found"+"(id:"+id+")");
        }
        Article article = articleDao.findById(id);
        return ArticleResponse.of(article.getId(),
                article.getBoard_id(),
                article.getAuthor_id(),
                article.getTitle(),
                article.getContent(),
                article.getCreated_date(),
                article.getModified_date());

    }

    @Transactional
    public ArticleResponse createArticle(ArticleCreate request) throws IllegalAccessException {
        if( memberDao.findById(request.author_id())==null){
            throw new IllegalAccessException("Member Not Found"+"(author_id:"+request.author_id()+")");
        }
        if(boardDao.findById(request.board_id())==null){
            throw new IllegalAccessException("Board Not Found"+"(id:"+request.board_id()+")");
        }



        Article toSave = articleDao.findById(request.author_id());
        toSave.setBoard_id(request.board_id());
        toSave.setAuthor_id(request.author_id());
        toSave.setTitle(request.title());
        toSave.setContent(request.content());
        LocalDateTime now = LocalDateTime.now();
        toSave.setCreated_date(now);
        toSave.setModified_date(now);

        Article saved = articleDao.insert(toSave);

        return ArticleResponse.of(
                saved.getId(),
                saved.getBoard_id(),
                saved.getAuthor_id(),
                saved.getTitle(),
                saved.getContent(),
                saved.getCreated_date(),
                saved.getModified_date()
        );

    }

    @Transactional
    public ArticleResponse updateArticle(ArticleUpdate update, Long id) throws IllegalAccessException {
        if(articleDao.findById(id)==null){
            throw new IllegalAccessException("Article with id: " + id + " not found!");
        }
        Article articles = articleDao.findById(id);

        if(update.title() != null && !update.title().isBlank()) {
            articles.setTitle(update.title());
        }
        if(update.content() != null && !update.content().isBlank()) {
            articles.setContent(update.content());
        }
        articles.setModified_date(LocalDateTime.now());
        articleDao.update(articles);
        return ArticleResponse.of(articles.getId(),
                articles.getBoard_id(),
                articles.getAuthor_id(),
                articles.getTitle(),
                articles.getContent(),
                articles.getCreated_date(),
                articles.getModified_date());
    }

    @Transactional
    public void deleteArticle(Long id) {
        if (!articleDao.delete(id)) {
            throw new NullPointerException("해당 id가 존재하지 않습니다.");
        }
    }


}
