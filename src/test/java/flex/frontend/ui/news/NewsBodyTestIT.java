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
public class NewsBodyTestIT extends UITest {
    
    public NewsBodyTestIT() {
    }

    @Test
    public void testGetBaseLayout() {
        System.out.println("getBaseLayout");
        ArticlesCount body = new ArticlesCount();
    }
    
    @Test
    public void testGetSourcesCount() {
        ArticlesCount body = new ArticlesCount();
        assertNotNull(body.getSourcesCount());
    }
    
    @Test
    public void testGetAuthorsCount() {
        ArticlesCount body = new ArticlesCount();
        assertNotNull(body.getAuthorsCount());
    }

    @Test
    public void testGetArticlesCount() {
        ArticlesCount body = new ArticlesCount();
        assertNotNull(body.getArticlesCount());
    }

}
