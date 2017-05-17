/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.services;

import flex.backend.news.db.Neo4jQueries;
import flex.backend.news.Neo4jSessionFactory;
import flex.backend.news.db.NewsArticle;
import java.util.HashMap;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.neo4j.ogm.session.Session;

/**
 *
 * @author zua
 */
@Stateless
@LocalBean
public class NewsArticleService extends  AbstractDBService<NewsArticle> {

    @Override
    public Class<NewsArticle> getClassType() {
        return NewsArticle.class;
    }
    
    public NewsArticle findArticleByTitle(String title) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.queryForObject(NewsArticle.class, 
                Neo4jQueries.getInstance().findArticleByTitle(title),
                new HashMap<>()); 
    }

    @Override
    protected NewsArticle update(NewsArticle dbEntity, NewsArticle newEntity) {
        if(newEntity.getAuthor() != null && !newEntity.getAuthor().equals(dbEntity.getAuthor())) {
            dbEntity.setAuthor(newEntity.getAuthor());
        }
        
        if(newEntity.getDescription() != null && newEntity.getDescription().equals(dbEntity.getDescription())) {
            dbEntity.setDescription(newEntity.getDescription());
        }
        
        if(newEntity.getImageUrl() != null && newEntity.getImageUrl().equals(dbEntity.getImageUrl())) {
            dbEntity.setImageUrl(newEntity.getImageUrl());
        }
        
        if(newEntity.getImageUrl() != null && newEntity.getImageUrl().equals(dbEntity.getImageUrl())) {
            dbEntity.setImageUrl(newEntity.getImageUrl());
        }

        if(newEntity.getPublishedAt() != null && newEntity.getPublishedAt().equals(dbEntity.getPublishedAt())) {
            dbEntity.setPublishedAt(newEntity.getPublishedAt());
        }

        if(newEntity.getSourceId() != null && newEntity.getSourceId().equals(dbEntity.getSourceId())) {
            dbEntity.setSourceId(newEntity.getSourceId());
        }
        
        if(newEntity.getTitle() != null && newEntity.getTitle().equals(dbEntity.getTitle())) {
            dbEntity.setTitle(newEntity.getTitle());
        }

        if(newEntity.getUrl() != null && newEntity.getUrl().equals(dbEntity.getUrl())) {
            dbEntity.setUrl(newEntity.getUrl());
        }
        
        return dbEntity;
    }
}
