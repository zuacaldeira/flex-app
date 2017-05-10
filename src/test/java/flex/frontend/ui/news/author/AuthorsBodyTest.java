/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news.author;

import flex.frontend.ui.news.UITest;
import org.junit.Test;
import org.utils.ServiceLocator;

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

        ServiceLocator.getInstance().findNewsApiService().loadData();

        AuthorsBody body = new AuthorsBody();
    }
}
