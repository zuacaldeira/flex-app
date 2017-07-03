package db.news;

import java.io.Serializable;
import java.util.Objects;
import org.neo4j.ogm.annotation.GraphId;

/**
 * Created by zua on 15/04/17.
 */
public abstract class GraphEntity implements Serializable {
    

    @GraphId
    private Long id;
    
    public GraphEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public abstract String getPropertyName();
    public abstract String getPropertyValue();
    public abstract boolean hasUrl();
    public abstract String getUrl();
    
    @Override
    public boolean equals(Object other) {
        if(other instanceof GraphEntity) {
            GraphEntity otherEntity = (GraphEntity) other;
            return getPropertyName().equals(otherEntity.getPropertyName()) 
                    && getPropertyValue().equals(otherEntity.getPropertyValue());
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(getPropertyName());
        hash = 23 * hash + Objects.hashCode(getPropertyValue());
        return hash;
    }
}
