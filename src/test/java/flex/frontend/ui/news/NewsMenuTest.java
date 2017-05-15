/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author zua
 */
public class NewsMenuTest {
    
    public NewsMenuTest() {
    }

    @Test
    public void testSomeMethod() {
        NewsMenu menu = new NewsMenu();
        assertNotNull(menu.getArticlesButton());
        assertNotNull(menu.getHomeButton());
        assertNotNull(menu.getSearchButton());        
    }
    
    @Test(expected = Exception.class)
    public void testClickHome() {
        NewsMenu menu = new NewsMenu();
        menu.getHomeButton().click();
    }

    @Test(expected = Exception.class)
    public void testClickArticles() {
        NewsMenu menu = new NewsMenu();
        menu.getArticlesButton().click();
    }


    @Test
    public void testClickSearch() {
        NewsMenu menu = new NewsMenu();
        menu.getSearchButton();
    }


}
