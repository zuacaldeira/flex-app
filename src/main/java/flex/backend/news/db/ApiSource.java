package flex.backend.news.db;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by zua on 12/04/17.
 */
@NodeEntity
public class ApiSource extends GraphEntity implements Comparable<ApiSource> {
    private String sourceId;
    private String name;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;
    
    private Set<Author> authors;
    
    public ApiSource() {
        authors = new HashSet<>();
    }

    public ApiSource(String id, String name, String description, String url, String category, String language, String country) {
        this();
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

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
    
    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(ApiSource o) {
        return name.compareTo(o.getName());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.sourceId);
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
        final ApiSource other = (ApiSource) obj;
        if (!Objects.equals(this.sourceId, other.sourceId)) {
            return false;
        }
        return true;
    }    

    public void addAuthor(Author author) {
        if(author == null) {
            throw new NullPointerException("Author cannot be null");
        }
        authors.add(author);
    }

}
