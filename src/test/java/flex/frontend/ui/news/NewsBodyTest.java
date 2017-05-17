/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news;

import flex.frontend.ui.FlexBody;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author zua
 */
public class NewsBodyTest extends UITest {
    
    public NewsBodyTest() {
    }

    @Test
    public void testGetBaseLayout() {
        System.out.println("getBaseLayout");
        ArticlesCount body = new ArticlesCount();
        assertTrue(body instanceof FlexBody);
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
