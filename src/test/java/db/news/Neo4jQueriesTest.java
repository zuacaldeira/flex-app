/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.news;

import db.news.Neo4jQueries;
import db.news.GraphEntity;
import db.news.NewsArticle;
import db.news.NewsAuthor;
import db.news.NewsSource;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class Neo4jQueriesTest {
    
    public Neo4jQueriesTest() {
    }

    @DataProvider
    public static Object[][] queryProvider() {
        return new Object[][] {
            {new NewsArticle("title", "description", "url", "imageUrl", new Date()), "MATCH (n:NewsArticle{title:\"title\"}) RETURN n"},
            {new NewsSource("sourceId", "name", "description", "url", "category", "language", "country"), "MATCH (n:NewsSource{sourceId:\"sourceId\"}) RETURN n"},
            {new NewsAuthor("name"), "MATCH (n:NewsAuthor{name:\"name\"}) RETURN n"}
        };
    }

    @DataProvider
    public static Object[][] idQueryProvider() {
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date());
        article.setId(100L);
        return new Object[][] {
            {article,
             "MATCH (n:NewsArticle{id:100}) RETURN n"}
        };
    }

    @DataProvider
    public static Object[][] articleProvider() {
        return new Object[][] {
            {new NewsArticle("title", "description", "url", "imageUrl", new Date()), "MATCH (n:NewsArticle{title:\"title\"}) RETURN n"}
        };
    }

    @DataProvider
    public static Object[][] sourceProvider() {
        return new Object[][] {
            {new NewsSource("sourceId", "name", "description", "url", "category", "language", "country"), "MATCH (n:NewsSource{sourceId:\"sourceId\"}) RETURN n"}
        };
    }

    @DataProvider
    public static Object[][] authorProvider() {
        return new Object[][] {
            {new NewsAuthor("name"), "MATCH (n:NewsAuthor{name:\"name\"}) RETURN n"}
        };
    }

    /**
     * Test of findQuery method, of class Neo4jQueries.
     */
    @Test
    @UseDataProvider("queryProvider")
    public void testFindQuery(GraphEntity entity, String query) {
        System.out.println("findQuery");
        String result = Neo4jQueries.getInstance().findQuery(entity);
        assertEquals(query, result);
    }

    /**
     * Test of findByIdQuery method, of class Neo4jQueries.
     */
    @Test
    @UseDataProvider("idQueryProvider")
    public void testFindByIdQuery(GraphEntity entity, String query) {
        System.out.println("findByIdQuery");
        String result = Neo4jQueries.getInstance().findByIdQuery(entity);
        assertEquals(query, result);
    }

    /**
     * Test of findSourceBySourceId method, of class Neo4jQueries.
     */
    @Test
    @UseDataProvider("sourceProvider")
    public void testFindSourceBySourceId(NewsSource entity, String query) {
        System.out.println("findSourceBySourceId");
        String result = Neo4jQueries.getInstance().findSourceBySourceId(entity.getSourceId());
        assertEquals(query, result);
    }

    /**
     * Test of findArticleByTitle method, of class Neo4jQueries.
     */
    @Test
    @UseDataProvider("articleProvider")
    public void testFindArticleByTitle(NewsArticle entity, String query) {
        System.out.println("findArticleByTitle");
        String result = Neo4jQueries.getInstance().findArticleByTitle(entity.getTitle());
        assertEquals(query, result);
    }

    /**
     * Test of findAuthorByName method, of class Neo4jQueries.
     */
    @Test
    @UseDataProvider("authorProvider")
    public void testFindAuthorByName(NewsAuthor entity, String query) {
        System.out.println("findAuthorByName");
        String result = Neo4jQueries.getInstance().findAuthorByName(entity.getName());
        assertEquals(query, result);
    }
    
    
    @Test
    public void testGetInstance() {
        assertNotNull(Neo4jQueries.getInstance());
    }
    
}
