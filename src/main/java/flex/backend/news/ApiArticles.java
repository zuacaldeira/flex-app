package flex.backend.news;


import flex.backend.news.db.ApiArticle;
import flex.backend.news.db.ApiSource;
import flex.backend.news.db.Author;
import flex.backend.news.db.UnknownAuthor;
import java.util.*;

/**
 * Created by zua on 13/04/17.
 */
public class ApiArticles {

    private Map<Author, Collection<ApiArticle>> articlesMap;
    private ApiSource source;

    public ApiArticles() {
        this.articlesMap = new TreeMap<>();
    }

    public ApiArticles(ApiSource source) {
        this();
        this.source = source;
    }

    public Map<Author, Collection<ApiArticle>> getArticlesMap() {
        return articlesMap;
    }

    public void setArticlesMap(Map<Author, Collection<ApiArticle>> articlesMap) {
        this.articlesMap = articlesMap;
    }


    public ApiSource getSource() {
        return source;
    }

    public void setSource(ApiSource source) {
        this.source = source;
    }
    
    public void addArticle(ApiArticle article) {
        Author author = getAuthor(article);
        if(articlesMap.get(author) == null) {
            articlesMap.put(author, new HashSet<>());
        }
        articlesMap.get(author).add(article);
    }

    private Author getAuthor(ApiArticle article) {
        if(article.getAuthor() == null) {
            return new UnknownAuthor();
        } else {
            return new Author(article.getAuthor(), null);
        }
    }
    

}
