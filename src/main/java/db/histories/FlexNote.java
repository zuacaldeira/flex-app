/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.histories;

import db.news.FlexUser;
import db.news.GraphEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 *
 * @author zua
 */
public class FlexNote extends GraphEntity {

    private String title;
    private String content;
    
    @Relationship(type = "OWNED_BY")
    private FlexUser owner;

    public FlexNote() {
    }

    @Override
    public String getPropertyName() {
        return "title";
    }

    @Override
    public String getPropertyValue() {
        return title;
    }

    @Override
    public boolean hasUrl() {
        return false;
    }

    @Override
    public String getUrl() {
        throw new UnsupportedOperationException();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public FlexUser getOwner() {
        return owner;
    }

    public void setOwner(FlexUser owner) {
        this.owner = owner;
    }
    
    
    
}
