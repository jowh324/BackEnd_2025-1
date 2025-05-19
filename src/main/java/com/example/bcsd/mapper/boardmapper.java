package com.example.bcsd.mapper;

import com.example.bcsd.Model.Board;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class boardmapper implements RowMapper<Board> {
    public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
        Board board = new Board();
        board.setId(rs.getLong("id"));
        board.setName(rs.getString("name"));
        return board;
    }
}
