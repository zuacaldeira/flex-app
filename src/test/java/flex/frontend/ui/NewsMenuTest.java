/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import flex.frontend.ui.news.NewsMenu;
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
        assertNotNull(menu.getAuthorsButton());
        assertNotNull(menu.getHomeButton());
        assertNotNull(menu.getMenuButton());
        assertNotNull(menu.getSearchButton());        
        assertNotNull(menu.getSourcesButton());        
    }
    
}
