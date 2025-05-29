package com.example.bcsd.repository;

import com.example.bcsd.Dao.ArticleDao;
import com.example.bcsd.Model.Article;
import com.example.bcsd.Model.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.*;

@Repository
@Transactional
public class ArticleRepository implements ArticleDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Article insert(Article article){
        em.persist(article);
        return article;
    }

    @Override
    public List<Article> findByBoardId(long board_id) {
       String jpql = "select a from Article a where a.board_id=:board_id";
        return em.createQuery(jpql,Article.class).setParameter("board_id",board_id).getResultList();
    }
    public List<Article> findByAuthorId(long author_id){
        String jpql = "select a from Article a where a.author_id=:author_id";
       return em.createQuery(jpql,Article.class).setParameter("author_id",author_id).getResultList();
    };

    @Override
    public Article findById(long id) {
        return em.find(Article.class, id);

    }


    public  Article update(Article article) {
        return em.merge(article);
    }

    public boolean delete(long id) {
        Article article = em.find(Article.class, id);
        if (article != null) {
            em.remove(article);
            return true;
        }
        return false;
    }




}
