/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.news.author;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import db.news.FlexUser;
import db.news.NewsAuthor;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import ui.news.UITest;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class AuthorViewTestIT extends UITest {
    
    public AuthorViewTestIT() {
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
     * @param view
     * @param author
     */
    @Test
    @UseDataProvider("authorsProvider")
    public void testGetInfo(AuthorView view, NewsAuthor author) {
        System.out.println("getInfo");
        assertNotNull(view.getInfoHeader());
    }

    /**
     * Test of getControls method, of class AuthorView.
     */
    @Test
    @UseDataProvider("authorsProvider")
    public void testGetControls(AuthorView view, NewsAuthor author) {
        System.out.println("getControls");
        assertNotNull(view.getInfoActions());
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
        AuthorView view = new AuthorView(new FlexUser(), author);
        return new Object[][] {
            {view, author}
        };
    }
    
}