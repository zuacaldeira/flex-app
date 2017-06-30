/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.histories;

import db.news.FlexUser;
import db.news.GraphEntity;
import java.util.Date;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 *
 * @author zua
 */
@NodeEntity
public class FlexVideo extends GraphEntity {

    @Relationship(type = "POSTED_BY", direction=Relationship.OUTGOING)
    private FlexUser postedBy;
    
    @Index(unique=true)
    private String url;    
    private Date postedAt;
    private String title;
    private String author;
    private String language;
    private String description;

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language;
    }

    public FlexUser getPostedBy() {
        return postedBy;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public void setPostedBy(FlexUser postedBy) {
        this.postedBy = postedBy;
    }


    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String getPropertyName() {
        return "url";
    }

    @Override
    public String getPropertyValue() {
        return url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean hasUrl() {
        return url != null;
    }

}
