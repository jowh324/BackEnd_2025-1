package com.example.bcsd.Service;

import com.example.bcsd.Model.Board;
import com.example.bcsd.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    public Board findById(long id) {
       return boardRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Board not found"));
    }
    @Transactional
    public Board insert(Board board) {
        return boardRepository.insert(board);
    }
    @Transactional
    public Board update(Board board) {
        return boardRepository.update(board);
    }
    @Transactional
    public int delete(Long id) {
        return delete(id);
    }

}
