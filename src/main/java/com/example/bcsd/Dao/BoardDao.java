package com.example.bcsd.Dao;

import com.example.bcsd.Model.Board;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface BoardDao {

    Board findById(long id);

    Board insert(Board board);

    Board update(Board board);

    boolean delete(long id);


}
