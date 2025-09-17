package com.newsag.dto;
import java.util.Date;

public class NewsItem {
    private String title;
    private String link;
    private String description;
    private Date publishedDate;

    public NewsItem(String title, String link, String description, Date publishedDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.publishedDate = publishedDate;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }
}
