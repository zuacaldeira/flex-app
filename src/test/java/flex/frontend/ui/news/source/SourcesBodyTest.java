/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news.source;

import flex.frontend.ui.news.UITest;
import javax.naming.NamingException;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class SourcesBodyTest extends UITest {
    
    public SourcesBodyTest() {
    }

    @Test
    public void testSomeMethod() throws NamingException {
        ServiceLocator.findNewsApiService().loadData();
        
        SourcesBody body = new SourcesBody();        
        assertNotNull(body.getGrid());
        assertTrue(body.getGrid().getRows() > 0);
    }
    
}
