package com.example.bcsd;

import com.example.bcsd.Model.Article;
import com.example.bcsd.repository.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class posts {
    private final ArticleRepository articleRepository;
    public posts(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("boardName", "자유게시판");
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("posts", articles);
        return "posts";
    }
}
