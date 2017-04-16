package flex.backend.db;

import org.neo4j.ogm.annotation.GraphId;

/**
 * Created by zua on 15/04/17.
 */
public abstract class GraphEntity {

    @GraphId
    private Long id;

    public Long getId() {
        return id;
    }
}
