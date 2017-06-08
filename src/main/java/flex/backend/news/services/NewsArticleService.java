/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.services;

import flex.backend.news.db.Neo4jQueries;
import flex.backend.news.Neo4jSessionFactory;
import flex.backend.news.db.NewsArticle;
import flex.backend.news.db.NewsSource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.cypher.Filters;
import org.neo4j.ogm.cypher.query.SortOrder;
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
    public NewsArticle update(NewsArticle dbEntity, NewsArticle newEntity) {
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
        return new SortOrder().add(SortOrder.Direction.DESC, "publishedAt");
    }

    public Set<NewsArticle> findAll(String category) {
        Filter filter = new Filter("category", ComparisonOperator.EQUALS, category);

        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        Collection<NewsSource> sources = session.loadAll(NewsSource.class, filter, getSortOrder(), 2);
        return collectArticles(sources);
    }

    public Set<NewsArticle> findAll(String category, String language) {
        Filter filterCategory = new Filter("category", ComparisonOperator.EQUALS, category);
        Filter filterLanguage = new Filter("language", ComparisonOperator.EQUALS, language);
        Filters filters = new Filters(filterCategory, filterLanguage);
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        Collection<NewsSource> sources = session.loadAll(NewsSource.class, filters, getSortOrder(), 2);
        return collectArticles(sources);
    }

    private Set<NewsArticle> collectArticles(Collection<NewsSource> sources) {
        Set<NewsArticle> articles = new TreeSet(new NewsArticlePublishedAtComparator());
        sources.forEach(s -> {
            s.getCorrespondents().forEach(author -> {
                articles.addAll(author.getArticles());
            });
        });
        return articles;
    }
}
