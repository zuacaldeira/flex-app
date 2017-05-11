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
        NewsBody body = new NewsBody();
        assertTrue(body instanceof FlexBody);
    }
    
    @Test
    public void testGetSourcesCount() {
        NewsBody body = new NewsBody();
        assertNotNull(body.getSourcesCount());
    }
    
    @Test
    public void testGetAuthorsCount() {
        NewsBody body = new NewsBody();
        assertNotNull(body.getAuthorsCount());
    }

    @Test
    public void testGetArticlesCount() {
        NewsBody body = new NewsBody();
        assertNotNull(body.getArticlesCount());
    }

}
