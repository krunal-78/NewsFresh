package com.example.newsfresh;

public  class News {
    String title;
    String author;
    String url;
    String imageUrl;
    News(String title,String author,String url,String imageUrl){
        this.author = author;
        this.title = title;
        this.url = url;
        this.imageUrl = imageUrl;
    }
}
