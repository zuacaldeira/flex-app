/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news.author;

import flex.frontend.ui.news.UITest;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author zua
 */
public class AuthorsBodyTest extends UITest {
    
    public AuthorsBodyTest() {
    }

    /**
     * Test of addAuthors method, of class AuthorsBody.
     */
    @Test
    public void testAddAuthors() {
        System.out.println("addAuthors");
        AuthorsBody body = new AuthorsBody();
        assertNotNull(body.getLayout());
        
    }
}
