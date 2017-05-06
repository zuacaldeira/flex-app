/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.services;

import flex.backend.news.db.Neo4jTest;
import flex.backend.news.db.NewsSource;
import org.junit.Test;
import static org.junit.Assert.*;

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
        instance.createOrUpdate(source);
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
        
        instance.createOrUpdate(source);
        
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
        
        instance.createOrUpdate(source);
        
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
     * Test of createOrUpdate method, of class NewsSourceService.
     */
    @Test
    public void testCreateOrUpdate() throws Exception {
        System.out.println("createOrUpdate");
        NewsSourceService instance = new NewsSourceService();
        assertFalse(instance.findAll().iterator().hasNext());
        
        NewsSource source = new NewsSource();
        source.setSourceId("sourceId");
        
        instance.createOrUpdate(source);
        
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
        
        instance.createOrUpdate(source);
        
        NewsSource dbSource = instance.findSourceBySourceId(source.getSourceId());
        assertNotNull(dbSource);
        assertNotNull(dbSource.getId());
        assertNotNull(instance.find(dbSource.getId()));
        assertEquals(dbSource, source);
    }
    
}
