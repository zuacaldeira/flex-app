/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news.source;

import flex.frontend.ui.news.UITest;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author zua
 */
public class SourcesBodyTest extends UITest {
    
    public SourcesBodyTest() {
    }

    @Test
    public void testSomeMethod() {
        SourcesBody body = new SourcesBody();        
        assertNotNull(body.getLayout());
    }
    
}
