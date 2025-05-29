package com.example.bcsd.Service;

import com.example.bcsd.Dao.ArticleDao;
import com.example.bcsd.Dao.BoardDao;
import com.example.bcsd.Dto.BoardCreate;
import com.example.bcsd.Dto.BoardResponse;
import com.example.bcsd.Dto.BoardUpdate;
import com.example.bcsd.Model.Board;
import com.example.bcsd.repository.BoardRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
    private final BoardDao boardDao;
    private final ArticleDao articleDao;

    public BoardService(BoardDao boardDao, ArticleDao articleDao) {
        this.articleDao = articleDao;
        this.boardDao = boardDao;
    }

    @Transactional
    public BoardResponse findById(long id) {
        if (boardDao.findById(id) == null) {
            throw new EntityNotFoundException("Board with id: " + id + " not found!");

        }
        Board board = boardDao.findById(id);
        return BoardResponse.of(
                board.getId(),
                board.getName()

        );
    }

    @Transactional
    public BoardResponse insert(BoardCreate board) {
        if (board.name().isEmpty()) {
            throw new EntityNotFoundException("Board name empty!");
        }
        Board board1 = new Board();
        board1.setName(board.name());
        Board board2 = boardDao.insert(board1);
        return BoardResponse.of(
                board2.getId(),
                board2.getName()
        );
    }

    @Transactional
    public BoardResponse update(BoardUpdate board, long id) {
        if (boardDao.findById(id) == null) {
            throw new EntityNotFoundException("Board with id: " + id + " not found!");
        }
        Board board1 = boardDao.findById(id);
        board1.setName(board.name());
        Board board2 = boardDao.update(board1);
        return BoardResponse.of(
                board2.getId(),
                board2.getName()
        );
    }

    @Transactional
    public void delete(Long id) throws IllegalAccessException {
        if (!articleDao.findByBoardId(id).isEmpty()) {
            throw new IllegalAccessException("연결된 article 존재");
        }


        boolean deleted = boardDao.delete(id);
        if (!deleted) {
            throw new EntityNotFoundException("해당 id가 존재하지 않습니다.");
        }
    }

}
