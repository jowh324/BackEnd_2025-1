package com.example.bcsd.repository;

import com.example.bcsd.Dao.BoardDao;
import com.example.bcsd.Model.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardRepository implements BoardDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Board findById(long id) {
       return em.find(Board.class, id);
    }


    @Override
    public Board insert(Board board) {


        em.persist(board);
        return board;
    }

    public Board update(Board board) {
        return em.merge(board);
    }

    public boolean delete(long id) {
       Board b = em.find(Board.class, id);
       if (b != null) {
           em.remove(b);
           return true;
       }
        return  false;
    }
}
