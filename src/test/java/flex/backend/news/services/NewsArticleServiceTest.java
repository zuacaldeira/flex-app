/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.services;

import flex.backend.news.db.Neo4jTest;
import flex.backend.news.db.NewsArticle;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author zua
 */
public class NewsArticleServiceTest extends Neo4jTest {
    
    public NewsArticleServiceTest() {
    }

    /**
     * Test of findAll method, of class NewsArticleService.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        NewsArticleService instance = new NewsArticleService();
        assertEquals(false, instance.findAll().iterator().hasNext());
        instance.createOrUpdate(new NewsArticle());
        assertEquals(true, instance.findAll().iterator().hasNext());
    }

    /**
     * Test of find method, of class NewsArticleService.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        NewsArticleService instance = new NewsArticleService();
        assertEquals(false, instance.findAll().iterator().hasNext());
        
        instance.createOrUpdate(new NewsArticle());        
        
        NewsArticle article = instance.findAll().iterator().next();
        assertNotNull(article);
        
        NewsArticle article2 = instance.find(article.getId());
        assertNotNull(article2);
        
        assertEquals(article, article2);
    }

    /**
     * Test of find method, of class NewsArticleService.
     */
    @Test
    public void testFindByTitle()  {
        System.out.println("findByTitle");
        NewsArticleService instance = new NewsArticleService();
        assertEquals(false, instance.findAll().iterator().hasNext());
        
        instance.createOrUpdate(new NewsArticle("title", "description", "url", "imageUrl", "publishedAt"));        
        
        NewsArticle article = instance.find("title");
        assertNotNull(article);        
    }

    /**
     * Test of delete method, of class NewsArticleService.
     */
    @Test
   public void testDelete() throws Exception {
        System.out.println("delete");
        NewsArticleService instance = new NewsArticleService();
        assertEquals(false, instance.findAll().iterator().hasNext());
        
        instance.createOrUpdate(new NewsArticle("title", "description", "url", "imageUrl", "publishedAt"));        
        
        NewsArticle article = instance.find("title");
        assertNotNull(article);        
        
        instance.delete(article.getId());
        
        article = instance.find("title");
        assertNull(article);                
    }

    /**
     * Test of createOrUpdate method, of class NewsArticleService.
     */
    @Test
    @Ignore
    public void testCreateOrUpdate() throws Exception {
        System.out.println("createOrUpdate");
        NewsArticleService instance = new NewsArticleService();
        assertEquals(false, instance.findAll().iterator().hasNext());
        
        instance.createOrUpdate(new NewsArticle("title", "description", "url", "imageUrl", "publishedAt"));        
        instance.createOrUpdate(new NewsArticle("title2", "description", "url", "imageUrl", "publishedAt"));        
        
        NewsArticle article = instance.find("title");
        assertNotNull(article);        
        
        NewsArticle article2 = instance.find("title2");
        assertNotNull(article);        

        instance.delete(article.getId());
        instance.delete(article2.getId());
        
        article = instance.find("title");
        article2 = instance.find("title2");

        assertNull(article);        
        assertNull(article2);        
    }

    
}
