package flex.backend.news.db;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by zua on 12/04/17.
 */
@NodeEntity
public class NewsSource extends GraphEntity implements Comparable<NewsSource> {

    @Index(unique=true)
    private String sourceId;
    private String name;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;
    
    @Relationship(type = "PUBLISHED", direction = Relationship.OUTGOING)
    private Set<NewsAuthor> correspondents;
    
    public NewsSource() {
        correspondents = new HashSet<>();
    }

    public NewsSource(String sourceId, String name, String description, String url, String category, String language, String country) {
        this();
        this.sourceId = sourceId;
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
        if(sourceId == null) {
            return "";
        }
        return sourceId;
    }

    @Override
    public int compareTo(NewsSource o) {
        return sourceId.compareTo(o.getSourceId());
    }


    public void addCorrespondent(NewsAuthor author) {
        correspondents.add(author);
        author.setSource(this);
    }

    public Set<NewsAuthor> getCorrespondents() {
        return correspondents;
    }

    public void setCorrespondents(Set<NewsAuthor> correspondents) {
        this.correspondents = correspondents;
        for(NewsAuthor author: correspondents) {
            addCorrespondent(author);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.sourceId);
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
        final NewsSource other = (NewsSource) obj;
        if (!Objects.equals(this.sourceId, other.sourceId)) {
            return false;
        }
        return true;
    }

    
    
    
    

}
