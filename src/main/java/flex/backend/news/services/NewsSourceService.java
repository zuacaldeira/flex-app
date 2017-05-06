/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.services;

import flex.backend.news.Neo4jSessionFactory;
import flex.backend.news.db.NewsSource;
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
public class NewsSourceService extends AbstractDBService<NewsSource> {

    @Override
    protected Class<NewsSource> getClassType() {
        return NewsSource.class;
    }
    
    public NewsSource findSourceBySourceId(String sourceId) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.queryForObject(NewsSource.class, 
                Neo4jQueries.findSourceBySourceId(sourceId), 
                new HashMap<>()); 
    }

}
