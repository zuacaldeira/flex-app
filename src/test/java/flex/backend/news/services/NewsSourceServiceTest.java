/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.services;

import flex.backend.news.db.Neo4jTest;
import flex.backend.news.db.NewsArticle;
import flex.backend.news.db.NewsAuthor;
import flex.backend.news.db.NewsSource;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author zua
 */
public class NewsSourceServiceTest extends Neo4jTest {
    
    public NewsSourceServiceTest() {
    }

    /**
     * Test of findAll method, of class NewsSourceService.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        NewsSourceService instance = new NewsSourceService();
        
        assertFalse(instance.findAll().iterator().hasNext());
        NewsSource source = new NewsSource();
        source.setSourceId("sourceId");
        instance.save(source);
        assertTrue(instance.findAll().iterator().hasNext());
        
    }

    /**
     * Test of findSourceBySourceId method, of class NewsSourceService.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        NewsSourceService instance = new NewsSourceService();
        assertFalse(instance.findAll().iterator().hasNext());
        
        NewsSource source = new NewsSource();
        source.setSourceId("sourceId");
        
        instance.save(source);
        
        NewsSource dbSource = instance.findSourceBySourceId(source.getSourceId());
        assertNotNull(dbSource);
        assertNotNull(dbSource.getId());
        assertNotNull(instance.find(dbSource.getId()));
        assertEquals(dbSource, source);
    }

    /**
     * Test of delete method, of class NewsSourceService.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        NewsSourceService instance = new NewsSourceService();
        assertFalse(instance.findAll().iterator().hasNext());
        
        NewsSource source = new NewsSource();
        source.setSourceId("sourceId");
        
        instance.save(source);
        
        NewsSource dbSource = instance.findSourceBySourceId(source.getSourceId());
        assertNotNull(dbSource);
        assertNotNull(dbSource.getId());
        assertNotNull(instance.find(dbSource.getId()));
        assertEquals(dbSource, source);
        
        instance.delete(dbSource.getId());

        dbSource = instance.findSourceBySourceId(source.getSourceId());
        assertNull(dbSource);
    }

    /**
     * Test of save method, of class NewsSourceService.
     */
    @Test
    public void testCreateOrUpdate() throws Exception {
        System.out.println("createOrUpdate");
        NewsSourceService instance = new NewsSourceService();
        assertFalse(instance.findAll().iterator().hasNext());
        
        NewsSource source = new NewsSource();
        source.setSourceId("sourceId");
        
        instance.save(source);
        
        NewsSource dbSource = instance.findSourceBySourceId(source.getSourceId());
        assertNotNull(dbSource);
        assertNotNull(dbSource.getId());
        assertNotNull(instance.find(dbSource.getId()));
        assertEquals(dbSource, source);
    }

    /**
     * Test of findSourceBySourceId method, of class NewsSourceService.
     */
    @Test
    public void testFindBySourceId() throws Exception {
        System.out.println("find");
        NewsSourceService instance = new NewsSourceService();
        assertFalse(instance.findAll().iterator().hasNext());
        
        NewsSource source = new NewsSource();
        source.setSourceId("sourceId");
        
        instance.save(source);
        
        NewsSource dbSource = instance.findSourceBySourceId(source.getSourceId());
        assertNotNull(dbSource);
        assertNotNull(dbSource.getId());
        assertNotNull(instance.find(dbSource.getId()));
        assertEquals(dbSource, source);
    }


    @Test
    @Ignore
    public void testSavePathFromSource() throws Exception {
        System.out.println("savePathFromSource");
        NewsSourceService instance = new NewsSourceService();
        assertFalse(instance.findAll().iterator().hasNext());
        
        NewsArticle article = new NewsArticle();
        article.setTitle("title");
        
        NewsAuthor author = new NewsAuthor("name");        
        author.addArticle(article);
        
        assertNotNull(article.getAuthor());
        assertNotNull(author.getArticles());
        assertFalse(author.getArticles().isEmpty());
        assertTrue(author.getArticles().contains(article));
        
        
        NewsSource source = new NewsSource("sourceId", "name", "description", "url", "category", "language", "country");
        source.addCorrespondent(author);
        
        assertNotNull(source.getCorrespondents());
        assertFalse(source.getCorrespondents().isEmpty());
        assertTrue(source.getCorrespondents().contains(author));
        
                
        NewsSource dbSource = instance.save(source);

        assertNotNull(source.getCorrespondents());
        assertNotNull(dbSource.getCorrespondents());

        assertFalse(source.getCorrespondents().isEmpty());
        assertFalse(dbSource.getCorrespondents().isEmpty());

        assertTrue(source.getCorrespondents().contains(author));
        assertTrue(dbSource.getCorrespondents().contains(author));        
    }
    
    
    
}
