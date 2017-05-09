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
    protected Class<NewsArticle> getClassType() {
        return NewsArticle.class;
    }
    
    public NewsArticle findArticleByTitle(String title) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.queryForObject(NewsArticle.class, 
                Neo4jQueries.findArticleByTitle(title),
                new HashMap<>()); 
    }

    @Override
    public NewsArticle save(NewsArticle object) {
        NewsArticle dbArticle = findArticleByTitle(object.getTitle());
        if(dbArticle == null) {
           Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
           session.save(object);
           dbArticle = findArticleByTitle(object.getTitle());
        }
        return dbArticle;
    }
}
