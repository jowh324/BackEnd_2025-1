package com.example.bcsd.repository;

import com.example.bcsd.Dao.BoardDao;
import com.example.bcsd.Model.Board;
import com.example.bcsd.mapper.boardmapper;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepository implements BoardDao {
    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Board> findById(long id) {
        String sql = "SELECT * FROM board where id=? ";
        List<Board> result = jdbcTemplate.query(sql, new boardmapper(), id);
        return result.stream().findFirst();
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
        }, keyHolder);

            board.setId(keyHolder.getKey().longValue());


        return board;
    }

    public Board update(Board board) {
        String sql = """
                UPDATE board
                   SET name = ?
                 WHERE id = ?
                """;
         jdbcTemplate.update(sql, board.getName(), board.getId());
         return board;
    }

    public boolean delete(long id) {
        return jdbcTemplate.update("delete from member where id = ?", id)>0;
    }
}
