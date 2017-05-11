/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.services;

import flex.backend.news.db.Neo4jQueries;
import flex.backend.news.Neo4jSessionFactory;
import flex.backend.news.db.NewsAuthor;
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
public class NewsAuthorService extends AbstractDBService<NewsAuthor> {

    @Override
    protected Class<NewsAuthor> getClassType() {
        return NewsAuthor.class;
    }
    
    public NewsAuthor findAuthorByName(String name) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.queryForObject(NewsAuthor.class, 
                Neo4jQueries.getInstance().findAuthorByName(name), 
                new HashMap<>()); 
    }

    @Override
    public NewsAuthor save(NewsAuthor author) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        NewsAuthor dbAuthor = findAuthorByName(author.getName());
        if(dbAuthor != null) {
            dbAuthor = update(dbAuthor, author);
        }
        else {
            dbAuthor = author;
        }
        session.save(dbAuthor, 2);
        return findAuthorByName(author.getName());
    }
    
    private NewsAuthor update(NewsAuthor dbAuthor, NewsAuthor author) {
        dbAuthor.setName(author.getName());
        dbAuthor.setSource(author.getSource());
        dbAuthor.setArticles(author.getArticles());
        return dbAuthor;
    }


}
