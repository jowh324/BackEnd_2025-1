package com.example.bcsd.Model;



public class Article {
    private String title;
    private String content;
    private Long id;
    private String name;
    private String date;


    public Article() {
    }

    public Article(Long id, String title, String content, String date, String name) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.name = name;
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

    public String getDate() {
        return date;
    }

    public void update(String title, String content, String date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return "title: "+title+"\ncontent: "+content+"\ndate: "+date+"\nname: "+name;
    }



}
