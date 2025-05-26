package com.example.bcsd.mapper;

import com.example.bcsd.Model.Article;
import com.example.bcsd.repository.ArticleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class articlemapper implements RowMapper<Article> {
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        Article article = new Article();
        article.setId(rs.getLong("id"));
        article.setAuthor_id(rs.getLong("author_id"));
        article.setBoard_id(rs.getLong("board_id"));
        article.setTitle(rs.getString("title"));
        article.setContent(rs.getString("content"));
        article.setCreated_date(rs.getTimestamp("created_date").toLocalDateTime());
        article.setModified_date(rs.getTimestamp("modified_date").toLocalDateTime());
        return article;
    }

}
