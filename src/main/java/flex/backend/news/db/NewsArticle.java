package flex.backend.news.db;


import java.util.Date;
import java.util.Objects;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by zua on 13/04/17.
 */
@NodeEntity
public class NewsArticle extends GraphEntity implements Comparable<NewsArticle>{

    @Index(unique=true)
    private String title;
    
    private String description;
    private String url;
    private String imageUrl;
    private Date publishedAt;
    private String sourceId;
    
    @Relationship(type = "AUTHORED", direction = Relationship.INCOMING)
    private NewsAuthor author;
    
    public NewsArticle(){
    }

    public NewsArticle(String title, String description, String url, String imageUrl, Date publishedAt) {
        this();
        this.title = title;
        this.description = description;
        this.url = url;
        this.imageUrl = imageUrl;
        this.publishedAt = publishedAt;
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

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public NewsAuthor getAuthor() {
        return author;
    }

    public void setAuthor(NewsAuthor author) {
        this.author = author;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.title);
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
        final NewsArticle other = (NewsArticle) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        if(title == null) {
            return "";
        }
        return title;
    }

    @Override
    public int compareTo(NewsArticle o) {
        if(this.title != null && o.title != null) {
            int result =  this.title.compareTo(o.title);
            if(result < 0) {
                return -1;
            }
            if(result > 0) {
                return 1;
            }
            else {
                return 0;
            }
        }
        else if(this.title == null && o.title!= null) {
            return 1;
        }
        
        else if(this.title != null && o.title == null) {
            return -1;
        } 
        else {
            return 0;
        }
    }

    @Override
    public String getPropertyName() {
        return "title";
    }

    @Override
    public String getPropertyValue() {
        return title;
    }
}
