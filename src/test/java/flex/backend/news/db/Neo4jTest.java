/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.db;

import flex.backend.news.Neo4jSessionFactory;
import java.util.HashMap;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.neo4j.ogm.session.Session;

/**
 *
 * @author zua
 */
public abstract class Neo4jTest {
    
    public Neo4jTest() {
        Neo4jSessionFactory.prepareForTest();
    }
    
    @Before
    @After
    public void cleanDatabase() {
        String query = "MATCH (n) DETACH DELETE n";
        Neo4jSessionFactory.prepareForTest();
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        session.query(query, new HashMap<>());
        assertEquals(0, session.countEntitiesOfType(NewsArticle.class));
        assertEquals(0, session.countEntitiesOfType(NewsAuthor.class));
        assertEquals(0, session.countEntitiesOfType(NewsSource.class));
    }
}
