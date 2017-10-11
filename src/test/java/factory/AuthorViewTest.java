/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import button.FakeButton;
import com.vaadin.ui.Button.ClickEvent;
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
        assertNotNull(authorView.getName());
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
        authorView.minimize();
        assertNotNull(authorView.getName());
    }

    /**
     * Test of maximize method, of class AuthorView.
     */
    @Test
    public void testMaximize() {
        System.out.println("maximize");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsAuthor author = new NewsAuthor("test:author");
        AuthorView authorView = new AuthorView(user, author);
        authorView.maximize();
        assertNotNull(authorView.getName());
    }

    /**
     * Test of createInfoHeader method, of class AuthorView.
     */
    @Test
    public void testCreateInfoHeader() {
        System.out.println("createInfoHeader");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsAuthor author = new NewsAuthor("test:author");
        AuthorView authorView = new AuthorView(user, author);
        assertNotNull(authorView.createInfoHeader());
    }

    /**
     * Test of createInfoBody method, of class AuthorView.
     */
    @Test
    public void testCreateInfoBody() {
        System.out.println("createInfoBody");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsAuthor author = new NewsAuthor("test:author");
        AuthorView authorView = new AuthorView(user, author);
        assertNotNull(authorView.createInfoBody());
    }

    /**
     * Test of createInfoActions method, of class AuthorView.
     */
    @Test
    public void testCreateInfoActions() {
        System.out.println("createInfoActions");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsAuthor author = new NewsAuthor("test:author");
        AuthorView authorView = new AuthorView(user, author);
        assertNotNull(authorView.createInfoActions());
    }

    /**
     * Test of buttonClick method, of class AuthorView.
     */
    @Test
    public void testButtonClick() {
        System.out.println("buttonClick");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsAuthor author = new NewsAuthor("test:author");
        AuthorView authorView = new AuthorView(user, author);
        assertNotNull(authorView.createInfoActions());
        authorView.buttonClick(new ClickEvent(new FakeButton()));
    }
    
}
