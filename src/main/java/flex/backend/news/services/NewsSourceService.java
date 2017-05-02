/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.services;

import flex.backend.news.Neo4jSessionFactory;
import flex.backend.news.db.ApiSource;
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
public class NewsSourceService extends AbstractDBService<ApiSource> {

    @Override
    protected Class<ApiSource> getClassType() {
        return ApiSource.class;
    }
    
    public ApiSource find(String sourceId) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        return session.queryForObject(
                ApiSource.class, 
                Neo4jQueries.findSourceBySourceId(sourceId), 
                new HashMap<>()); 
    }

}
