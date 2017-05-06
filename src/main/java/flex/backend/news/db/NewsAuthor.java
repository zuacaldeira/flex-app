package flex.backend.news.db;

import java.util.Objects;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by zua on 15/04/17.
 */
@NodeEntity
public class NewsAuthor extends  GraphEntity implements Comparable<NewsAuthor>{

    
    private String name;
    private String url;
    
    public NewsAuthor() {
    }

    public NewsAuthor(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int compareTo(NewsAuthor o) {
        return (int) Math.signum(this.toString().compareTo(o.toString()));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.name);
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
        final NewsAuthor other = (NewsAuthor) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if(name != null) {
            builder.append(name);
        }
        return builder.toString();
    }

    
    

}
