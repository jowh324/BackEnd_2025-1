package com.example.bcsd.controller;

import com.example.bcsd.Service.BoardService;
import com.example.bcsd.Model.Board;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
public class BoardController {
    private final BoardService boardService;
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    @PostMapping("/board")
    public ResponseEntity<Board> addBoard(@RequestBody Board board) {
        Board newBoard = boardService.insert(board);
        return ResponseEntity.ok(board);
    }
    @PutMapping("/board/{id}")
    public ResponseEntity<Board> uqdateBoard(@PathVariable Long id, @RequestBody Map<String, Object> payload) {

        Board board = boardService.findById(id);
        if (board == null) {
            return ResponseEntity.notFound().build();
        }
        board.setName((String) payload.get("name"));
        boardService.update(boardService.findById(board.getId()));
        // 변경된 정보를 다시 조회해서 돌려줄 수도 있습니다.
        return ResponseEntity.ok(boardService.findById(id));
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        int row = boardService.delete(id);
        if (row == 0) {
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }


}
