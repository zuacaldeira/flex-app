/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.services;

import flex.backend.news.db.Neo4jQueries;
import flex.backend.news.Neo4jSessionFactory;
import flex.backend.news.db.NewsArticle;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.cypher.Filters;
import org.neo4j.ogm.cypher.query.SortOrder;
import org.neo4j.ogm.session.Session;
import org.utils.FlexUtils;

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
    public NewsArticle update(NewsArticle dbEntity, NewsArticle newEntity) {
        if(newEntity.getAuthors() != null && !newEntity.getAuthors().equals(dbEntity.getAuthors())) {
            dbEntity.setAuthors(newEntity.getAuthors());
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
    
    @Override
    public boolean contains(NewsArticle object) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        Filter filter1 = new Filter(object.getPropertyName(), ComparisonOperator.EQUALS, object.getPropertyValue());
        Filter filter2 = new Filter("sourceId", ComparisonOperator.EQUALS, object.getSourceId());
        return !session.loadAll(getClassType(),
                               new Filters().add(filter1), 
                               2).isEmpty();
    }

    @Override
    public SortOrder getSortOrder() {
        return getSortOrderDesc();
    }

    private SortOrder getSortOrderDesc() {
        return new SortOrder().add(SortOrder.Direction.DESC, "publishedAt");
    }
    
    private SortOrder getSortOrderAsc() {
        return new SortOrder().add(SortOrder.Direction.ASC, "publishedAt");
    }
    
    public Iterable<NewsArticle> findArticlesWithCategory(String category, int limit) {
        String query = "MATCH (source:NewsSource)--(author:NewsAuthor)--(article:NewsArticle)";
        query += " WHERE source.category=" + FlexUtils.getInstance().wrapUp(category);
        query += " RETURN article ";
        query += " ORDER BY article.publishedAt DESC LIMIT " + limit;
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.query(getClassType(), query, new HashMap<>());
    }

    public Iterable<NewsArticle> findArticlesWithSource(String publisher) {
        String query = "MATCH (source:NewsSource)--(author:NewsAuthor)--(article:NewsArticle)";
        query += " WHERE source.name=" + FlexUtils.getInstance().wrapUp(publisher);
        query += " RETURN article ";
        query += " ORDER BY article.publishedAt DESC LIMIT " + MAX_SIZE;
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.query(getClassType(), query, new HashMap<>());
    }

    public Iterable<NewsArticle> findArticlesLatest() {
        return findAll(MAX_SIZE, getSortOrderDesc());
    }

    public Iterable<NewsArticle> findArticlesOldest() {
        return findAll(MAX_SIZE, getSortOrderAsc());
    }

    public Iterable<NewsArticle> findArticlesToday() {
        Filter filter = new Filter("publishedAt", ComparisonOperator.LESS_THAN_EQUAL, new Date());
        return findAll(MAX_SIZE, filter, getSortOrderDesc());
    }

    public Iterable<NewsArticle> findArticlesThisWeek() {
        return new HashSet<>(); 
    }

    public Iterable<NewsArticle> findArticlesThisMonth() {
        return new HashSet<>(); 
    }

    public Iterable<NewsArticle> findArticlesRead(String username) {
        String query = "MATCH (n:NewsArticle)<-[r:READ]-(u:FlexUser) RETURN n";
        return getSession().query(getClassType(), query, new HashMap<>());
    }
    
    public Iterable<NewsArticle> findArticlesUnread(String username) {
        return new HashSet<>(); 
    }
    
    public Iterable<NewsArticle> findArticlesFavorite(String username) {
        String query = "MATCH (n:NewsArticle)<-[r:FAVORITE]-(u:FlexUser) RETURN n";
        return getSession().query(getClassType(), query, new HashMap<>());
    }
    
    public Iterable<NewsArticle> findArticlesFake(String username) {
        String query = "MATCH (n:NewsArticle)<-[r:FAKE]-(u:FlexUser) RETURN n";
        return getSession().query(getClassType(), query, new HashMap<>());
    }
}
