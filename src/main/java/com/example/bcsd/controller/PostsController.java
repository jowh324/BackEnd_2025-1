package com.example.bcsd.controller;

import com.example.bcsd.Dao.ArticleDao;
import com.example.bcsd.Dao.BoardDao;
import com.example.bcsd.Model.Article;
import com.example.bcsd.Model.Board;
import com.example.bcsd.Service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class PostsController {
    private final BoardService boardService;
    private ArticleDao articleDao;

    public PostsController(ArticleDao articleDao,BoardService boardService) {
        this.articleDao = articleDao;
        this.boardService = boardService;
    }

    @GetMapping("/posts")
    public String viewPosts(@RequestParam("boardId") Long board_id, Model model) {
        Board board = boardService.findById(board_id);
        List<Article> list = articleDao.findByBoardId(board_id);

        model.addAttribute("boardName", board.getName());
        model.addAttribute("posts", list);
        return "posts";
    }
}
