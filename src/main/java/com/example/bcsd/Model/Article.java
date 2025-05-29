package com.example.bcsd.Model;


import com.example.bcsd.repository.ArticleRepository;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "article")
@EntityListeners(AuditingEntityListener.class)
public class Article {
    @Column(name="title")
    private String title;
    @Lob
    @Column(name="content")
    private String content;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="author_id")
    private Long author_id;
    @Column(name="board_id")
    private Long board_id;
    @Column(name="created_date")
    @CreatedDate
    private LocalDateTime created_date;
    @Column(name="modified_date")
    @LastModifiedDate
    private LocalDateTime modified_date;

    public Article() {
    }

    public Article(Long id, String title, String content, LocalDateTime created_date, LocalDateTime modified_date, Long author_id, Long board_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.created_date = created_date;
        this.modified_date = modified_date;
        this.author_id = author_id;
        this.board_id = board_id;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public Long getBoard_id() {
        return board_id;
    }

    public void setBoard_id(Long board_id) {
        this.board_id = board_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void update(String title, String content, LocalDateTime modified_date) {
        this.title = title;
        this.content = content;
        this.modified_date = modified_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public LocalDateTime getModified_date() {
        return modified_date;
    }

    public void setModified_date(LocalDateTime modified_date) {
        this.modified_date = modified_date;
    }

    public String toString() {
        return "title: " + title + "\ncontent: " + content + "\ndate: " + created_date + "\nname: " + author_id + "\nboard_id: " + board_id + "\nmodified_date: " + modified_date;
    }


}
