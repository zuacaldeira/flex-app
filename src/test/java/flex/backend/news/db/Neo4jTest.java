/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.db;

import flex.backend.news.Neo4jSessionFactory;
import flex.backend.news.db.NewsArticle;
import flex.backend.news.db.NewsAuthor;
import flex.backend.news.db.NewsSource;
import java.util.HashMap;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.neo4j.ogm.session.Session;

/**
 *
 * @author zua
 */
public abstract class Neo4jTest {
    
    @After
    public void cleanDatabase() {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        String query = "MATCH (n) DETACH DELETE n";
        session.query(query, new HashMap<>());
        assertEquals(0, session.countEntitiesOfType(NewsArticle.class));
        assertEquals(0, session.countEntitiesOfType(NewsAuthor.class));
        assertEquals(0, session.countEntitiesOfType(NewsSource.class));
    }
    
    
}
