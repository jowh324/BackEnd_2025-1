package com.example.bcsd.repository;

import com.example.bcsd.Dao.BoardDao;
import com.example.bcsd.Model.Board;
import com.example.bcsd.expection.expection;
import com.example.bcsd.mapper.boardmapper;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
@Repository
public class BoardRepository implements BoardDao {
    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Board> findByBoardId(long id) {
        String sql = "SELECT * FROM board where id=? ";
        return jdbcTemplate.query(sql, new boardmapper(), id);
    }

    @Override
    public Board findById(long id) {
        try {
            String sql = "SELECT * FROM board where id=? ";
            return jdbcTemplate.queryForObject(sql, new boardmapper(), id);
        } catch (EmptyResultDataAccessException e) {
            throw new expection("Board id=" + id + " not found");        }
    }

    @Override
    public Board insert(Board board) {
        String sql = """
                INSERT INTO board
                ( name) 
                values ( ?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, board.getName());

            return ps;
        },keyHolder);
        if (keyHolder != null) {
            board.setId(keyHolder.getKey().longValue());
        } else {
            throw new DataRetrievalFailureException("Failed to retrieve generated key for Member");
        }        return board;
    }
    public int update(Board board) {
        String sql = """
            UPDATE board
               SET name = ?
             WHERE id = ?
            """;
        return jdbcTemplate.update(sql, board.getName(),board.getId());
    }
    public int delete(long id) {
        return jdbcTemplate.update("delete from member where id = ?", id);
    }
}
