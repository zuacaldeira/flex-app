package flex.backend.db;


import java.text.ParseException;
import java.util.*;

/**
 * Created by zua on 13/04/17.
 */
public class ApiArticles {

    private Map<String, ApiArticle> articlesMap;

    public ApiArticles() {
        this.articlesMap = new TreeMap<>();
    }

    public void addArticle(String sourceId, String author, String title, String description, String url, String imageUrl, String publishedAt) {
        try {
            ApiArticle article = new ApiArticle(sourceId, author, title, description, url, imageUrl, publishedAt);
            articlesMap.put(title, article);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public ApiArticle getArticle(String title) {
        return articlesMap.get(title);
    }

    public Collection<ApiArticle> getArticles() {
        return articlesMap.values();
    }
}
