package flex.backend.news.db;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by zua on 15/04/17.
 */
@NodeEntity
public class Author extends  GraphEntity implements Comparable<Author>{

    private String twitter;
    
    private String name;
    
    private Set<ApiArticle> articles;
    

    public Author() {
        articles = new HashSet<>();
    }

    public Author(String name, String twitter) {
        this();
        this.name = name;
        this.twitter = twitter;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ApiArticle> getArticles() {
        return articles;
    }

    public void setArticles(Set<ApiArticle> articles) {
        this.articles = articles;
    }

    public void addArticle(ApiArticle article) {
        if(article == null) {
            throw new NullPointerException("Article cannot be null");
        }
        articles.add(article);
    }

    @Override
    public int compareTo(Author o) {
        if(o.name == null) {
            return -1;
        }
        else if(this.name == null) {
            return 1;
        }
        else {
            return this.name.compareTo(o.name);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    

}
