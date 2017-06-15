package flex.backend.news.db;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by zua on 15/04/17.
 */
@NodeEntity
public class NewsAuthor extends  GraphEntity implements Comparable<NewsAuthor>{

    public static NewsAuthor UNKNOWN = new NewsAuthor("Unknown");

    /**
     * Author's name. 
     * 
     * Names are unique, and the db has a uniqueness constraint on this 
     * property.
     */
    @Index(unique=true)
    private String name;
    
    /**
     * Author's online presence url.
     */
    private String url;

    /**
     * The news articles authored or co-authored by this author.
     */
    @Relationship(type = "AUTHORED", direction = Relationship.OUTGOING)
    private Set<NewsArticle> articles;
    
    /**
     * The organizations that published a writing belonging to this author.
     */
    @Relationship(type = "PUBLISHED", direction = Relationship.INCOMING)
    private NewsSource source;

    public NewsAuthor() {
        articles = new HashSet<>();
        name = "Unknown";
    }

    public NewsAuthor(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<NewsArticle> getArticles() {
        return articles;
    }

    public void setArticles(Set<NewsArticle> articles) {
        this.articles = articles;
        for(NewsArticle article: articles) {
            addArticle(article);
        }
    }
    
    

    @Override
    public int compareTo(NewsAuthor o) {
        return (int) Math.signum(this.toString().compareTo(o.toString()));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NewsAuthor other = (NewsAuthor) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if(name != null) {
            builder.append(name);
        }
        return builder.toString();
    }

    public void addArticle(NewsArticle article) {
        articles.add(article);
        article.getAuthors().add(this);
    }

    public void setSource(NewsSource source) {
        this.source = source;
    }

    public NewsSource getSource() {
        return source;
    }

    @Override
    public String getPropertyName() {
        return "name";
    }

    @Override
    public String getPropertyValue() {
        return name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean hasUrl() {
        return url != null;
    }
    
    

    

}
