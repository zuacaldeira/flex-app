/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.main;

import view.main.FlexNewsView;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import db.FlexUser;
import org.junit.Test;
import static org.junit.Assert.*;
import view.body.NewsBody;
import view.footer.FlexFooter;
import view.menu.NewsMenu;

/**
 *
 * @author zua
 */
public class FlexMainViewTest {

    private static final String TEST_USERNAME = "test:username";
    private static final String TEST_PASSWORD = "test:password";
    
    public FlexMainViewTest() {
    }
    
    
    @Test
    public void testConstructor() {
        System.out.println("new");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexNewsView mainView = new FlexNewsView();
        assertNull(mainView.getUser());
        assertNull(mainView.getMenu());
        assertNull(mainView.getBody());
        assertNull(mainView.getFooter());
    }

    /**
     * Test of setMenu method, of class FlexNewsView.
     */
    @Test
    public void testSetMenu() {
        System.out.println("setMenu");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexNewsView mainView = new FlexNewsView();
        NewsMenu menu = new NewsMenu(user);
        mainView.setMenu(menu);
        assertEquals(menu, mainView.getMenu());
    }

    /**
     * Test of setFooter method, of class FlexNewsView.
     */
    @Test
    public void testSetFooter() {
        System.out.println("setFooter");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexNewsView mainView = new FlexNewsView();
        FlexFooter footer = new FlexFooter(user);
        mainView.setFooter(footer);
        assertEquals(footer, mainView.getFooter());
    }

    /**
     * Test of getMenu method, of class FlexNewsView.
     */
    @Test
    public void testGetMenu() {
        System.out.println("getMenu");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexNewsView mainView = new FlexNewsView();
        NewsMenu menu = new NewsMenu(user);
        mainView.setMenu(menu);
        assertEquals(menu, mainView.getMenu());
    }

    /**
     * Test of getBody method, of class FlexNewsView.
     */
    @Test
    public void testGetBody() {
        System.out.println("getBody");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexNewsView mainView = new FlexNewsView();
        assertNull(mainView.getBody());
    }

    /**
     * Test of getFooter method, of class FlexNewsView.
     */
    @Test
    public void testGetFooter() {
        System.out.println("getFooter");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexNewsView mainView = new FlexNewsView();
        assertNull(mainView.getFooter());
    }

    /**
     * Test of replaceBody method, of class FlexNewsView.
     */
    @Test
    public void testReplaceBody() {
        System.out.println("replaceBody");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexNewsView mainView = new FlexNewsView();
        NewsBody newBody = new NewsBody(user);
        mainView.replaceBody(newBody);
        assertEquals(newBody, mainView.getBody());
    }

    /**
     * Test of getUser method, of class FlexNewsView.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexNewsView mainView = new FlexNewsView();
        assertNull(mainView.getUser());
    }

    /**
     * Test of populate method, of class FlexNewsView.
     */
    @Test
    public void testPopulate() {
        System.out.println("populate");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexNewsView mainView = new FlexNewsView();
        mainView.populate();
    }
    
}
