/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.db;

import flex.backend.news.Neo4jSessionFactory;
import java.util.HashMap;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.neo4j.ogm.session.Session;

/**
 *
 * @author zua
 */
public class Neo4jDatabaseOperationsTest extends Neo4jTest {
    
    
    @Test
    public void createNewsArticle() {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        String query = "MATCH (n:NewsArticle) RETURN n";

        NewsArticle dbArticle1 = session.queryForObject(NewsArticle.class, query, new HashMap<>());
        assertNull(dbArticle1);

        session.save(new NewsArticle());

        NewsArticle dbArticle = session.queryForObject(NewsArticle.class, query, new HashMap<>());
        assertNotNull(dbArticle);
        
        session.deleteAll(NewsArticle.class);
    }

    @Test
    public void createNewsSource() {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        String query = "MATCH (n:NewsSource) RETURN n";

        NewsSource dbSource1 = session.queryForObject(NewsSource.class, query, new HashMap<>());
        assertNull(dbSource1);

        session.save(new NewsSource());

        NewsSource dbSource = session.queryForObject(NewsSource.class, query, new HashMap<>());
        assertNotNull(dbSource);
        
        session.deleteAll(NewsSource.class);
    }

    @Test
    public void createNewsAuthor() {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        String query = "MATCH (n:NewsAuthor) RETURN n";

        NewsAuthor dbAuthor1 = session.queryForObject(NewsAuthor.class, query, new HashMap<>());
        assertNull(dbAuthor1);

        session.save(new NewsAuthor());

        NewsAuthor dbAuthor = session.queryForObject(NewsAuthor.class, query, new HashMap<>());
        assertNotNull(dbAuthor);
        
        session.deleteAll(NewsAuthor.class);
    }


    @Test
    public void createPublishes() {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        String query = "MATCH (n)-[r:PUBLISHES]->(m) RETURN r";

        Publishes dbPublishes1 = session.queryForObject(Publishes.class, query, new HashMap<>());
        assertNull(dbPublishes1);

        session.save(new Publishes(new NewsSource(), new NewsAuthor()));

        Publishes dbPublishes = session.queryForObject(Publishes.class, query, new HashMap<>());
        assertNotNull(dbPublishes);
        
        session.deleteAll(Publishes.class);
    }


    @Test
    public void createWrittenBy() {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        String query = "MATCH (n)-[r:WRITTEN_BY]->(m) RETURN r";

        WrittenBy dbWrittenBy1 = session.queryForObject(WrittenBy.class, query, new HashMap<>());
        assertNull(dbWrittenBy1);

        session.save(new WrittenBy(new NewsAuthor(), new NewsArticle()));

        WrittenBy dbWrittenBy = session.queryForObject(WrittenBy.class, query, new HashMap<>());
        assertNotNull(dbWrittenBy);
        
        session.deleteAll(WrittenBy.class);
    }



}
