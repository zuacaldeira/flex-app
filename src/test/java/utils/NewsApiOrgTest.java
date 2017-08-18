/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import utils.NewsApiOrg;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class NewsApiOrgTest {
    
    public NewsApiOrgTest() {
    }

    /**
     * Test of getSourceParams method, of class NewsApiOrg.
     */
    @Test
    public void testGetSourceParams() {
        System.out.println("getSourceParams");
        Map<String, String> result = NewsApiOrg.getSourceParams();
        assertEquals(3, result.keySet().size());
        assertTrue(result.keySet().contains("category"));
        assertTrue(result.keySet().contains("language"));
        assertTrue(result.keySet().contains("country"));
    }

    /**
     * Test of getArticlesParams method, of class NewsApiOrg.
     */
    @Test
    public void testGetArticlesParams() {
        System.out.println("getArticlesParams");
        Map<String, String> result = NewsApiOrg.getArticlesParams();
        assertEquals(3, result.keySet().size());
        assertTrue(result.keySet().contains("source"));
        assertTrue(result.keySet().contains("sortBy"));
        assertTrue(result.keySet().contains("apiKey"));
    }
    
    @Test
    public void testGetInstance() {
        NewsApiOrg nao = NewsApiOrg.getInstance();
        assertNotNull(nao);
        
        NewsApiOrg nao2 = NewsApiOrg.getInstance();
        assertTrue(nao == nao2);
    }
    
}
