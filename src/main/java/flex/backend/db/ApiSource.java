package flex.backend.db;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by zua on 12/04/17.
 */
@NodeEntity
public class ApiSource extends GraphEntity {
    private String sourceId;
    private String name;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;

    public ApiSource() {}

    public ApiSource(String id, String name, String description, String url, String category, String language, String country) {
        this.sourceId = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.category = category;
        this.language = language;
        this.country = country;
    }

    public String getSourceId() {
        return sourceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return category;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return name;
    }

}
