package com.example.bcsd.controller;

import com.example.bcsd.Dao.ArticleDao;
import com.example.bcsd.Dao.BoardDao;
import com.example.bcsd.Dto.ArticleResponse;
import com.example.bcsd.Dto.BoardResponse;
import com.example.bcsd.Model.Article;
import com.example.bcsd.Model.Board;
import com.example.bcsd.Service.ArticleService;
import com.example.bcsd.Service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class PostsController {
    private final BoardService boardService;
    private ArticleService articleService;

    public PostsController(ArticleService articleService,BoardService boardService) {
        this.articleService = articleService;
        this.boardService = boardService;
    }

    @GetMapping("/posts")
    public String viewPosts(@RequestParam("boardId") Long board_id, Model model) {
        BoardResponse board = boardService.findById(board_id);
        List<ArticleResponse> list = articleService.getArticlesByBoardId(board_id);

        model.addAttribute("boardName", board.name());
        model.addAttribute("posts", list);
        return "posts";
    }
}
