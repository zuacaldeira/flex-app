/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import db.FlexUser;
import db.NewsAuthor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class AuthorViewTest {
    
    public AuthorViewTest() {
    }

    /**
     * Test constructor.
     */
    @Test
    public void testNew() {
        System.out.println("new AuthorView()");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsAuthor author = new NewsAuthor("test:author");
        AuthorView authorView = new AuthorView(user, author);
        assertNotNull(authorView);
    }
    /**
     * Test of getName method, of class AuthorView.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsAuthor author = new NewsAuthor("test:author");
        AuthorView authorView = new AuthorView(user, author);
        assertNull(authorView.getName());
    }

    /**
     * Test of getAuthor method, of class AuthorView.
     */
    @Test
    public void testGetAuthor() {
        System.out.println("getAuthor");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsAuthor author = new NewsAuthor("test:author");
        AuthorView authorView = new AuthorView(user, author);
        assertNotNull(authorView.getAuthor());
    }

    /**
     * Test of minimize method, of class AuthorView.
     */
    @Test
    public void testMinimize() {
        System.out.println("minimize");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsAuthor author = new NewsAuthor("test:author");
        AuthorView authorView = new AuthorView(user, author);
        authorView.unselect();
        assertNull(authorView.getName());
    }

    /**
     * Test of select method, of class AuthorView.
     */
    @Test
    public void testMaximize() {
        System.out.println("maximize");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsAuthor author = new NewsAuthor("test:author");
        AuthorView authorView = new AuthorView(user, author);
        authorView.select();
        assertNull(authorView.getName());
    }


    
}
