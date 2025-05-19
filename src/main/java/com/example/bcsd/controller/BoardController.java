package com.example.bcsd.controller;

import com.example.bcsd.Dao.BoardDao;
import com.example.bcsd.Model.Board;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
public class BoardController {
    private BoardDao boardDao;
    public BoardController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }
    @PostMapping("/board")
    public ResponseEntity<Board> addBoard(@RequestBody Board board) {
        Board newBoard = boardDao.insert(board);
        return ResponseEntity.ok(board);
    }
    @PutMapping("/board/{id}")
    public ResponseEntity<Board> uqdateBoard(@PathVariable Long id, @RequestBody Map<String, Object> payload) {

        Board board = boardDao.findById(id);
        if (board == null) {
            return ResponseEntity.notFound().build();
        }
        board.setName((String) payload.get("name"));
        boardDao.update(boardDao.findById(board.getId()));
        // 변경된 정보를 다시 조회해서 돌려줄 수도 있습니다.
        return ResponseEntity.ok(boardDao.findById(id));
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        int row = boardDao.delete(id);
        if (row == 0) {
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }


}
