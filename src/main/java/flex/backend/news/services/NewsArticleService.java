/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.services;

import flex.backend.news.Neo4jSessionFactory;
import flex.backend.news.db.ApiArticle;
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
public class NewsArticleService extends  AbstractDBService<ApiArticle> {

    @Override
    protected Class<ApiArticle> getClassType() {
        return ApiArticle.class;
    }
    
    public ApiArticle find(String title) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.queryForObject(
                ApiArticle.class, 
                Neo4jQueries.findArticleByTitle(title), 
                new HashMap<>()); 
    }

    @Override
    public void createOrUpdate(ApiArticle object) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        session.queryForObject(
                ApiArticle.class, 
                Neo4jQueries.mergeArticle(object), 
                new HashMap<>()); 
    }
}
