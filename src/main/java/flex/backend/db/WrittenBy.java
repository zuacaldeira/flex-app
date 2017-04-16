package flex.backend.db;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by zua on 15/04/17.
 */
@RelationshipEntity(type = "WRITTEN_BY")
public class WrittenBy extends GraphEntity {

    @StartNode
    private ApiArticle article;

    @EndNode
    private Author author;

    public WrittenBy() {}

    public WrittenBy(ApiArticle article, Author author) {
        this.article = article;
        this.author = author;
    }


    public ApiArticle getArticle() {
        return article;
    }

    public void setArticle(ApiArticle article) {
        this.article = article;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    
    @Override
    public String toString() {
        return article.getTitle() + ", by " + author.getName();
    }
}
