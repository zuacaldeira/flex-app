package flex.backend.db;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by zua on 15/04/17.
 */
@RelationshipEntity(type = "PUBLISHED_BY")
public class PublishedBy extends GraphEntity {

    @StartNode
    private ApiArticle article;

    @EndNode
    private ApiSource source;

    public PublishedBy() {}

    public PublishedBy(ApiArticle article, ApiSource source) {
        this.article = article;
        this.source = source;
    }

    public ApiArticle getArticle() {
        return article;
    }

    public void setArticle(ApiArticle article) {
        this.article = article;
    }

    public ApiSource getSource() {
        return source;
    }

    public void setSource(ApiSource source) {
        this.source = source;
    }
    
        @Override
    public String toString() {
        return article.getTitle() + ", according to " + source.getName();
    }

}
