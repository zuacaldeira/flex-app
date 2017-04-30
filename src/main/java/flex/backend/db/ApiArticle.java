package flex.backend.db;


import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by zua on 13/04/17.
 */
@NodeEntity
public class ApiArticle extends GraphEntity {

    private String sourceId;
    private String author;
    private String title;
    private String description;
    private String url;
    private String imageUrl;
    private String publishedAt;

    public ApiArticle(String sourceId, String author, String title, String description, String url, String imageUrl, String publishedAt) {
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

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public void setAuthor(String author) {
        this.author = author;
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
}
