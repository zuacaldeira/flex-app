/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news;

import db.news.Neo4jQueries;
import utils.Neo4jSessionFactory;
import db.news.NewsSource;
import java.util.HashMap;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.neo4j.ogm.cypher.query.SortOrder;
import org.neo4j.ogm.session.Session;

/**
 *
 * @author zua
 */
@Stateless
@LocalBean
public class NewsSourceService extends AbstractDBService<NewsSource> {

    @Override
    public Class<NewsSource> getClassType() {
        return NewsSource.class;
    }
    
    public NewsSource findSourceBySourceId(String sourceId) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.queryForObject(NewsSource.class, 
                Neo4jQueries.getInstance().findSourceBySourceId(sourceId),
                new HashMap<>()); 
    }

    @Override
    public NewsSource update(NewsSource dbEntity, NewsSource newEntity) {
        if(newEntity.getCategory() != null) {
            dbEntity.setCategory(newEntity.getCategory());
        }
        if(newEntity.getCorrespondents() != null) {
            dbEntity.getCorrespondents().addAll(newEntity.getCorrespondents());
        }
        if(newEntity.getCountry() != null) {
            dbEntity.setCountry(newEntity.getCountry());
        }
        if(newEntity.getDescription() != null) {
            dbEntity.setDescription(newEntity.getDescription());
        }
        if(newEntity.getLanguage()!= null) {
            dbEntity.setLanguage(newEntity.getLanguage());
        }
        if(newEntity.getSourceId() != null) {
            dbEntity.setSourceId(newEntity.getSourceId());
        }
        if(newEntity.getUrl() != null) {
            dbEntity.setUrl(newEntity.getUrl());
        }
        if(newEntity.getLogoUrl() != null) {
            dbEntity.setLogoUrl(newEntity.getLogoUrl());
        }
        
        return dbEntity;
    }
    
    @Override
    public SortOrder getSortOrderAsc() {
        return new SortOrder().add(SortOrder.Direction.ASC, "name");
    }

    @Override
    public SortOrder getSortOrderDesc() {
        return new SortOrder().add(SortOrder.Direction.DESC, "name");
    }

    public Iterable<String> findCategories() {
        String query = "MATCH (s:NewsSource) RETURN DISTINCT s.category ORDER BY s.category ASC";
        Session session = super.getSession();
        return session.query(String.class, query, new HashMap());
    }

    public Iterable<String> findNames() {
        String query = "MATCH (s:NewsSource) RETURN DISTINCT s.name ORDER BY s.name ASC";
        Session session = super.getSession();
        return session.query(String.class, query, new HashMap());
    }


}
