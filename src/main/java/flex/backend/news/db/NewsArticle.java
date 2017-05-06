package flex.backend.news.db;


import java.util.Objects;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by zua on 13/04/17.
 */
@NodeEntity
public class NewsArticle extends GraphEntity implements Comparable<NewsArticle>{

    private String title;
    private String description;
    private String url;
    private String imageUrl;
    private String publishedAt;
    private String sourceId;

    public NewsArticle(){
    }

    public NewsArticle(String title, String description, String url, String imageUrl, String publishedAt) {
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

    public String getPublishedAt() {
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

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
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
        if(o.title == null) {
            return -1;
        }
        else if(this.title == null) {
            return 1;
        }
        else {
            return this.title.compareTo(o.title);
        }
    }
}
