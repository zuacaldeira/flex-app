/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.news;

import services.news.NewsArticleService;
import db.news.Neo4jTest;
import db.news.NewsArticle;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class NewsArticleServiceTestIT extends Neo4jTest {
    
    public NewsArticleServiceTestIT() {
    }

    /**
     * Test of findAll method, of class NewsArticleService.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        NewsArticleService instance = new NewsArticleService();
        assertEquals(false, instance.findAll().iterator().hasNext());
        NewsArticle article = new NewsArticle();
        article.setTitle("An article without a title, is not an article...");
        instance.save(article);
        assertEquals(true, instance.findAll().iterator().hasNext());
    }

    /**
     * Test of findArticleByTitle method, of class NewsArticleService.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        NewsArticleService instance = new NewsArticleService();
        assertEquals(false, instance.findAll().iterator().hasNext());
        
        NewsArticle article = new NewsArticle();
        article.setTitle("A Title");
        instance.save(article);        
        
        NewsArticle dbArticle = instance.findAll().iterator().next();
        assertNotNull(dbArticle);
        
        NewsArticle article2 = instance.find(dbArticle.getId());
        assertNotNull(article2);
        
        assertEquals(dbArticle, article2);
    }

    /**
     * Test of findArticleByTitle method, of class NewsArticleService.
     */
    @Test
    public void testFindByTitle()  {
        System.out.println("findByTitle");
        NewsArticleService instance = new NewsArticleService();
        assertEquals(false, instance.findAll().iterator().hasNext());
        
        instance.save(new NewsArticle("title", "description", "url", "imageUrl", new Date()));        
        
        NewsArticle article = instance.findArticleByTitle("title");
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
        
        instance.save(new NewsArticle("title", "description", "url", "imageUrl", new Date()));        
        
        NewsArticle article = instance.findArticleByTitle("title");
        assertNotNull(article);        
        
        instance.delete(article.getId());
        
        article = instance.findArticleByTitle("title");
        assertNull(article);                
    }

    /**
     * Test of save method, of class NewsArticleService.
     */
    @Test
    public void testSave() throws Exception {
        System.out.println("createOrUpdate");
        NewsArticleService instance = new NewsArticleService();
        assertEquals(false, instance.findAll().iterator().hasNext());
        
        instance.save(new NewsArticle("title", "description", "url", "imageUrl", new Date()));        
        instance.save(new NewsArticle("title2", "description", "url", "imageUrl", new Date()));        
        
        NewsArticle article = instance.findArticleByTitle("title");
        assertNotNull(article);        
        
        NewsArticle article2 = instance.findArticleByTitle("title2");
        assertNotNull(article);        

        instance.delete(article.getId());
        instance.delete(article2.getId());
        
        article = instance.findArticleByTitle("title");
        article2 = instance.findArticleByTitle("title2");

        assertNull(article);        
        assertNull(article2);        
    }
    
    @Test
    public void testSaveTwice() throws Exception {
        System.out.println("createOrUpdate");
        NewsArticleService instance = new NewsArticleService();
        assertEquals(false, instance.findAll().iterator().hasNext());
        
        NewsArticle article = new NewsArticle("title", "description", "url", "imageUrl", new Date());
        article = instance.save(article);        
        
        assertNotNull(article);
        assertEquals("title", article.getTitle());

        article.setTitle("title1");
        article = instance.save(article);     
        
        assertNotNull(article);                
        assertEquals("title1", article.getTitle());
    }
}