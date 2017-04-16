package flex.backend.db;

import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by zua on 15/04/17.
 */
@NodeEntity
public class Author extends  GraphEntity {

    private String twitter;
    private String name;

    public Author() {
    }

    public Author(String name, String twitter) {
        this.name = name;
        this.twitter = twitter;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
