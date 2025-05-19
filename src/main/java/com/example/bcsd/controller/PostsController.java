package com.example.bcsd.controller;

import com.example.bcsd.Dao.ArticleDao;
import com.example.bcsd.Dao.BoardDao;
import com.example.bcsd.Model.Article;
import com.example.bcsd.Model.Board;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class PostsController {
    private ArticleDao articleDao;
    private BoardDao boardDao;

    public PostsController(ArticleDao articleDao, BoardDao boardDao) {
        this.articleDao = articleDao;
        this.boardDao = boardDao;

    }

    @GetMapping("/posts")
    public String viewPosts(@RequestParam("boardId") Long board_id, Model model) {
        Board board = boardDao.findById(board_id);
        List<Article> list = articleDao.findByBoardId(board_id);

        model.addAttribute("boardName", board.getName());
        model.addAttribute("posts", list);
        return "posts";
    }
}
