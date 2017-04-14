package org.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by zua on 13/04/17.
 */
public class ApiArticle {
    private final String sourceId;
    private final String author;
    private final String title;
    private final String description;
    private final String url;
    private final String imageUrl;
    private final String publishedAt;

    public ApiArticle(String sourceId, String author, String title, String description, String url, String imageUrl, String publishedAt) throws ParseException {
        this.sourceId = sourceId;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.imageUrl = imageUrl;
        this.publishedAt = publishedAt;
    }


    public String getSourceId() {
        return sourceId;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPublishedAt() {
        return publishedAt;
    }
}
