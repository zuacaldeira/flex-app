/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news.author;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import flex.backend.news.db.NewsAuthor;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class AuthorViewTest {
    
    public AuthorViewTest() {
    }

    /**
     * Test of getName method, of class AuthorView.
     */
    @Test
    @UseDataProvider("authorsProvider")
    public void testGetName(AuthorView view, NewsAuthor author) {
        System.out.println("getName");
        assertEquals(author.getName(), view.getName().getValue());
    }

    /**
     * Test of getInfo method, of class AuthorView.
     */
    @Test
    @UseDataProvider("authorsProvider")
    public void testGetInfo(AuthorView view, NewsAuthor author) {
        System.out.println("getInfo");
        assertNotNull(view.getInfo());
    }

    /**
     * Test of getControls method, of class AuthorView.
     */
    @Test
    @UseDataProvider("authorsProvider")
    public void testGetControls(AuthorView view, NewsAuthor author) {
        System.out.println("getControls");
        assertNotNull(view.getControls());
    }

    /**
     * Test of getAuthor method, of class AuthorView.
     */
    @Test
    @UseDataProvider("authorsProvider")
    public void testGetAuthor(AuthorView view, NewsAuthor author) {
        System.out.println("getAuthor");
        assertEquals(author, view.getAuthor());
    }
    
    @DataProvider
    public static Object[][] authorsProvider() {
        NewsAuthor author = new NewsAuthor("name");
        AuthorView view = new AuthorView(author);
        return new Object[][] {
            {view, author}
        };
    }
    
}
