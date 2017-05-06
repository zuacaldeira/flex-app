package flex.backend.news;


import flex.backend.news.db.NewsArticle;
import flex.backend.news.db.NewsSource;
import flex.backend.news.db.NewsAuthor;
import java.util.*;

/**
 * Created by zua on 13/04/17.
 */
public class ApiArticles {

    private Map<NewsAuthor, Collection<NewsArticle>> articlesMap;
    private NewsSource source;

    public ApiArticles() {
        this.articlesMap = new TreeMap<>();
    }

    public ApiArticles(NewsSource source) {
        this();
        this.source = source;
    }

    public Map<NewsAuthor, Collection<NewsArticle>> getArticlesMap() {
        return articlesMap;
    }

    public void setArticlesMap(Map<NewsAuthor, Collection<NewsArticle>> articlesMap) {
        this.articlesMap = articlesMap;
    }


    public NewsSource getSource() {
        return source;
    }

    public void setSource(NewsSource source) {
        this.source = source;
    }
    
    public void addArticle(NewsArticle article, Set<NewsAuthor> authors) {
        for(NewsAuthor author: authors) {
            if(!articlesMap.containsKey(author)) {
                articlesMap.put(author, new HashSet<>());
            }
            articlesMap.get(author).add(article);
        }
    }
}
