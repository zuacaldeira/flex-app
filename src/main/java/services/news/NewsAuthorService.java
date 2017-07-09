/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news;

import db.news.Neo4jQueries;
import utils.Neo4jSessionFactory;
import db.news.NewsAuthor;
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
public class NewsAuthorService extends AbstractDBService<NewsAuthor> {

    @Override
    public Class<NewsAuthor> getClassType() {
        return NewsAuthor.class;
    }
    
    public NewsAuthor findAuthorByName(String name) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.queryForObject(NewsAuthor.class, 
                Neo4jQueries.getInstance().findAuthorByName(name), 
                new HashMap<>()); 
    }

    @Override
    public NewsAuthor update(NewsAuthor dbEntity, NewsAuthor newEntity) {
        if(newEntity.getName() != null && !newEntity.getName().equals(dbEntity.getName())) {
            dbEntity.setName(newEntity.getName());
        }
        
        if(newEntity.getSource() != null && !newEntity.getSource().equals(dbEntity.getSource())) {
            dbEntity.setSource(newEntity.getSource());
        }
        
        if(newEntity.getArticles() != null) {
            dbEntity.getArticles().addAll(newEntity.getArticles());
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
    

}
