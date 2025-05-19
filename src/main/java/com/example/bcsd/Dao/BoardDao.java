package com.example.bcsd.Dao;

import com.example.bcsd.Model.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BoardDao {
    List<Board> findByBoardId(long board_id);

    Board findById(long id);

    Board insert(Board board);

    int update(Board board);

    int delete(long id);

}
