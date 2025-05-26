package com.example.bcsd.controller;

import com.example.bcsd.Dto.BoardCreate;
import com.example.bcsd.Dto.BoardResponse;
import com.example.bcsd.Dto.BoardUpdate;
import com.example.bcsd.Service.BoardService;
import com.example.bcsd.Model.Board;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;
@RestController
public class BoardController {
    private final BoardService boardService;
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    @PostMapping("/board")
    public ResponseEntity<BoardResponse> addBoard(@RequestBody BoardCreate board) {
        BoardResponse newBoard = boardService.insert(board);
        return ResponseEntity.created(URI.create("/board")).body(newBoard);
    }
    @PutMapping("/board/{id}")
    public ResponseEntity<BoardResponse> uqdateBoard(@PathVariable Long id, @RequestBody BoardUpdate board) {

       BoardResponse res=boardService.update(board,id);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) throws IllegalAccessException {
        boardService.delete(id);

        return ResponseEntity.noContent().build();
    }


}
