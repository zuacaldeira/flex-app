/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.db;

import flex.backend.news.Neo4jSessionFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.neo4j.harness.ServerControls;
import org.neo4j.harness.TestServerBuilders;
import org.neo4j.ogm.session.Session;

/**
 *
 * @author zua
 */
public abstract class Neo4jTest {

    private static ServerControls testServer;

    @BeforeClass
    public static void setupTestServer() throws IOException {
        File db = new File("~/tmp/flex-db");
        if(!db.exists()) {
            testServer = TestServerBuilders.newInProcessBuilder(db).newServer();
        }
        else {
            testServer = TestServerBuilders.newInProcessBuilder().copyFrom(db).newServer();
        }
    }

    @AfterClass
    public static void teardownTestServer() {
        if(testServer != null) {
            testServer.close();
        }
    }


    @Before
    public void cleanDatabase() {
        Neo4jSessionFactory.prepareForTest(testServer.httpURI().getPath());
        String query = "MATCH (n) DETACH DELETE n";
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        session.query(query, new HashMap<>());
        assertEquals(0, session.countEntitiesOfType(NewsArticle.class));
        assertEquals(0, session.countEntitiesOfType(NewsAuthor.class));
        assertEquals(0, session.countEntitiesOfType(NewsSource.class));
    }
}
