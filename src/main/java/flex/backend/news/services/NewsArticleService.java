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
                Neo4jQueries.getInstance().findArticleByTitle(title),
                new HashMap<>()); 
    }

    @Override
    public NewsArticle save(NewsArticle article) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        NewsArticle dbArticle = findArticleByTitle(article.getTitle());
        if(dbArticle != null) {
            dbArticle = update(dbArticle, article);
        }
        else {
            dbArticle = article;
        }
        session.save(dbArticle, 1);
        return findArticleByTitle(article.getTitle());
    }

    private NewsArticle update(NewsArticle dbArticle, NewsArticle article) {
        dbArticle.setAuthor(article.getAuthor());
        dbArticle.setDescription(article.getDescription());
        dbArticle.setImageUrl(article.getImageUrl());
        dbArticle.setPublishedAt(article.getPublishedAt());
        dbArticle.setSourceId(article.getSourceId());
        dbArticle.setTitle(article.getTitle());
        dbArticle.setUrl(article.getUrl());
        return dbArticle;
    }
}
