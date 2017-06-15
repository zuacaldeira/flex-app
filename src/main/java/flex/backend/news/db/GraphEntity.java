package flex.backend.news.db;

import org.neo4j.ogm.annotation.GraphId;

/**
 * Created by zua on 15/04/17.
 */
public abstract class GraphEntity {
    

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
}
