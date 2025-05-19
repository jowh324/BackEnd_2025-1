package com.example.bcsd.repository;

import com.example.bcsd.Dao.ArticleDao;
import com.example.bcsd.Model.Article;
import com.example.bcsd.mapper.articlemapper;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class ArticleRepository implements ArticleDao {
    private final JdbcTemplate jdbcTemplate;

    public ArticleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Article> findByBoardId(long board_id) {
        String sql = "SELECT * FROM article where board_id=? ";
        return jdbcTemplate.query(sql, new articlemapper(), board_id);
    }

    @Override
    public Article findById(long id) {

        String sql = "SELECT * FROM article where id=? ";
        return jdbcTemplate.queryForObject(sql, new articlemapper(), id);


    }
    @Override
    public Article insert(Article article) {
        String sql = """
                INSERT INTO article
                (board_id, author_id,  title, content, created_date, modified_date) 
                values (?, ?, ?, ?, NOW(), NOW())
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setLong(1, article.getBoard_id());
            ps.setLong(2, article.getAuthor_id());
            ps.setString(3, article.getTitle());
            ps.setString(4, article.getContent());
            return ps;
        },keyHolder);
        if (keyHolder != null) {
            article.setId(keyHolder.getKey().longValue());
        } else {
            throw new DataRetrievalFailureException("Failed to retrieve generated key for Member");
        }        article.setCreated_date(LocalDateTime.now());
        article.setModified_date(LocalDateTime.now());
        return article;
    }
    public int update(Article article) {
        String sql = """
            UPDATE article
               SET title = ?, content = ?, modified_date = NOW()
             WHERE id = ?
            """;
        return jdbcTemplate.update(sql, article.getTitle(), article.getContent(), article.getId());
    }
    public boolean delete(long id) {
        return jdbcTemplate.update("delete from article where id = ?", id)>0;
    }
}
