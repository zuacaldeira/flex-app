/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.services;

import flex.backend.news.db.Neo4jTest;
import flex.backend.news.db.NewsAuthor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class NewsAuthorServiceTest extends Neo4jTest {
    
    public NewsAuthorServiceTest() {
    }

    /**
     * Test of findAll method, of class NewsAuthorService.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        NewsAuthorService instance = new NewsAuthorService();
        assertEquals(false, instance.findAll().iterator().hasNext());
        instance.save(new NewsAuthor("name"));
        assertEquals(true, instance.findAll().iterator().hasNext());
    }

    /**
     * Test of findAuthorByName method, of class NewsAuthorService.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        NewsAuthorService instance = new NewsAuthorService();
        assertEquals(false, instance.findAll().iterator().hasNext());
        
        instance.save(new NewsAuthor("name"));        
        
        NewsAuthor article = instance.findAll().iterator().next();
        assertNotNull(article);
        
        NewsAuthor article2 = instance.find(article.getId());
        assertNotNull(article2);
        
        assertEquals(article, article2);
    }

    /**
     * Test of findAuthorByName method, of class NewsAuthorService.
     */
    @Test
    public void testFindByName()  {
        System.out.println("findByName");
        NewsAuthorService instance = new NewsAuthorService();
        assertEquals(false, instance.findAll().iterator().hasNext());
        
        NewsAuthor author = new NewsAuthor("name");
        instance.save(author);        
        
        NewsAuthor article = instance.findAuthorByName("name");
        assertNotNull(article);        
    }

    /**
     * Test of delete method, of class NewsAuthorService.
     */
    @Test
    //@Ignore
   public void testDelete() throws Exception {
        System.out.println("delete");
        NewsAuthorService instance = new NewsAuthorService();
        assertEquals(false, instance.findAll().iterator().hasNext());
        
        instance.save(new NewsAuthor("name"));        
        
        NewsAuthor article = instance.findAuthorByName("name");
        assertNotNull(article);        
        
        instance.delete(article.getId());
        
        article = instance.findAuthorByName("name");
        assertNull(article);                
    }

    /**
     * Test of save method, of class NewsAuthorService.
     */
    @Test
    public void testCreateOrUpdate() throws Exception {
        System.out.println("createOrUpdate");
        NewsAuthorService instance = new NewsAuthorService();
        assertEquals(false, instance.findAll().iterator().hasNext());
        
        instance.save(new NewsAuthor("name1"));        
        instance.save(new NewsAuthor("name2"));        
        
        NewsAuthor article = instance.findAuthorByName("name1");
        assertNotNull(article);        
        
        NewsAuthor article2 = instance.findAuthorByName("name2");
        assertNotNull(article);        

        instance.delete(article.getId());
        instance.delete(article2.getId());
        
        article = instance.findAuthorByName("name1");
        article2 = instance.findAuthorByName("name2");

        assertNull(article);        
        assertNull(article2);        
    }

    
}
