/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.db;

import java.util.Objects;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 *
 * @author zua
 */
@RelationshipEntity(type="WRITTEN_BY")
public class WrittenBy extends GraphEntity {
    @StartNode
    private NewsAuthor author;
    
    @EndNode
    private NewsArticle article;

    public WrittenBy() {
    }

    public WrittenBy(NewsAuthor author, NewsArticle article) {
        this.author = author;
        this.article = article;
    }

    public NewsAuthor getAuthor() {
        return author;
    }

    public void setAuthor(NewsAuthor author) {
        this.author = author;
    }

    public NewsArticle getArticle() {
        return article;
    }

    public void setArticle(NewsArticle article) {
        this.article = article;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.author);
        hash = 71 * hash + Objects.hashCode(this.article);
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
        final WrittenBy other = (WrittenBy) obj;
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.article, other.article)) {
            return false;
        }
        return true;
    }
}
