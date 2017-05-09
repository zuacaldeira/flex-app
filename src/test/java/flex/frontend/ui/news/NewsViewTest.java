/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news;

import flex.frontend.ui.news.article.ArticlesBody;
import flex.frontend.ui.FlexFooter;
import flex.frontend.ui.MainViewTest;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class NewsViewTest extends MainViewTest {
    
    public NewsViewTest() {
    }

    /**
     * Test of getMenu method, of class NewsView.
     */
    @Test
    public void testGetMenu() {
        System.out.println("getMenu");
        NewsView instance = new NewsView();
        assertNotNull(instance.getMenu());
    }
    
  
    @Override
    public void testSetMenu() {
        System.out.println("setMenu");
        NewsView instance = new NewsView();
        assertNotNull(instance.getMenu());
        
        NewsMenu menu = new NewsMenu();
        instance.setMenu(menu);
        
        assertTrue(instance.getComponentIndex(menu) >= 0);
    }

    /**
     * Test of getBody method, of class NewsView.
     */
    @Test
    public void testGetBody() {
        System.out.println("getBody");
        NewsView instance = new NewsView();
        assertNotNull(instance.getBody());
        
        ArticlesBody body = new ArticlesBody();
        instance.setBody(body);
        
        assertTrue(instance.getComponentIndex(body) >= 0);
    }

    /**
     * Test of setBody method, of class NewsView.
     */
    @Test
    public void testSetBody() {
        System.out.println("setBody");
        NewsView instance = new NewsView();
        assertNotNull(instance.getBody());
        
        ArticlesBody body = new ArticlesBody();
        instance.setBody(body);
        
        assertTrue(instance.getComponentIndex(body) >= 0);
    }

    /**
     * Test of getFooter method, of class NewsView.
     */
    @Test
    public void testGetFooter() {
        System.out.println("getFooter");
        NewsView instance = new NewsView();
        assertNotNull(instance.getFooter());
        
        FlexFooter footer = new FlexFooter();
        instance.setFooter(footer);
        
        assertTrue(instance.getComponentIndex(footer) >= 0);
    }


    @Override
    public void testSetFooter() {
        NewsView instance = new NewsView();
        assertNotNull(instance.getFooter());
        
        FlexFooter footer = new FlexFooter();
        instance.setFooter(footer);
        
        assertTrue(instance.getComponentIndex(footer) >= 0);
    }
    
}
