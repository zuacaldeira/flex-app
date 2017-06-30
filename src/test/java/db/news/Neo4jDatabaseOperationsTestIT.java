/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.news;

import db.news.NewsArticle;
import db.news.NewsAuthor;
import db.news.NewsSource;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import utils.Neo4jSessionFactory;
import java.util.Date;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.ogm.session.Session;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class Neo4jDatabaseOperationsTestIT extends Neo4jTest {
    
    
    
    @DataProvider
    public static Object[][] articlesProvider() {
        NewsArticle article1 = new NewsArticle();
        NewsArticle article2 = new NewsArticle("title", "description", "url", "imageUrl", new Date());
        return new Object[][] {
            {article1},
            {article2}
        };
    }
    @DataProvider
    public static Object[][] sourcesProvider() {
        NewsSource source1 = new NewsSource();
        NewsSource source2 = new NewsSource("sourceId", "name", "description", "url", "category", "language", "country");
        return new Object[][] {
            {source1},
            {source2}
        };
    }
    @DataProvider
    public static Object[][] authorsProvider() {
        NewsAuthor author1 = new NewsAuthor();
        author1.setName("name1");        
        NewsAuthor author2 = new NewsAuthor();
        author2.setName("name2");
        return new Object[][] {
            {author1},
            {author2}
        };
    }
    
    @Test
    @UseDataProvider("articlesProvider")
    public void createNewsArticle(NewsArticle article) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        String query = "MATCH (n:NewsArticle) RETURN n";

        NewsArticle dbArticle1 = session.queryForObject(NewsArticle.class, query, new HashMap<>());
        assertNull(dbArticle1);

        assertNull(article.getId());
        session.save(article);

        NewsArticle dbArticle2 = session.queryForObject(NewsArticle.class, query, new HashMap<>());
        assertNotNull(dbArticle2);
        
        assertNotNull(dbArticle2.getId());
        assertEquals(dbArticle2, article);
    }

    @Test
    @UseDataProvider("sourcesProvider")
    public void createNewsSource(NewsSource source) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        String query = "MATCH (n:NewsSource) RETURN n";

        NewsSource dbSource1 = session.queryForObject(NewsSource.class, query, new HashMap<>());
        assertNull(dbSource1);

        assertNull(source.getId());
        session.save(source);

        NewsSource dbSource = session.queryForObject(NewsSource.class, query, new HashMap<>());
        assertNotNull(dbSource);
        
        assertNotNull(dbSource.getId());
        assertEquals(dbSource, source);
    }

    @Test
    @UseDataProvider("authorsProvider")
    public void createNewsAuthor(NewsAuthor author) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        String query = "MATCH (n:NewsAuthor) RETURN n";

        NewsAuthor dbAuthor1 = session.queryForObject(NewsAuthor.class, query, new HashMap<>());
        assertNull(dbAuthor1);

        assertNull(author.getId());
        session.save(author);

        NewsAuthor dbAuthor = session.queryForObject(NewsAuthor.class, query, new HashMap<>());
        assertNotNull(dbAuthor);
        
        assertEquals(dbAuthor, author);
        assertNotNull(dbAuthor.getId());
    }
}
