/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.main;

import db.FlexUser;
import org.junit.Test;
import static org.junit.Assert.*;
import ui.view.body.FlexBody;
import ui.view.footer.FlexFooter;
import ui.view.menu.FlexMenu;

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
        FlexMainView mainView = new FlexMainView(user);
        assertNotNull(mainView.getUser());
        assertNotNull(mainView.getMenu());
        assertNotNull(mainView.getBody());
        assertNotNull(mainView.getFooter());
    }

    /**
     * Test of setMenu method, of class FlexMainView.
     */
    @Test
    public void testSetMenu() {
        System.out.println("setMenu");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMainView mainView = new FlexMainView(user);
        FlexMenu menu = new FlexMenu(user);
        mainView.setMenu(menu);
        assertEquals(menu, mainView.getMenu());
    }

    /**
     * Test of setFooter method, of class FlexMainView.
     */
    @Test
    public void testSetFooter() {
        System.out.println("setFooter");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMainView mainView = new FlexMainView(user);
        FlexFooter footer = new FlexFooter(user);
        mainView.setFooter(footer);
        assertEquals(footer, mainView.getFooter());
    }

    /**
     * Test of getMenu method, of class FlexMainView.
     */
    @Test
    public void testGetMenu() {
        System.out.println("getMenu");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMainView mainView = new FlexMainView(user);
        FlexMenu menu = new FlexMenu(user);
        mainView.setMenu(menu);
        assertEquals(menu, mainView.getMenu());
    }

    /**
     * Test of getBody method, of class FlexMainView.
     */
    @Test
    public void testGetBody() {
        System.out.println("getBody");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMainView mainView = new FlexMainView(user);
        assertNotNull(mainView.getBody());
    }

    /**
     * Test of getFooter method, of class FlexMainView.
     */
    @Test
    public void testGetFooter() {
        System.out.println("getFooter");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMainView mainView = new FlexMainView(user);
        assertNotNull(mainView.getFooter());
    }

    /**
     * Test of replaceBody method, of class FlexMainView.
     */
    @Test
    public void testReplaceBody() {
        System.out.println("replaceBody");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMainView mainView = new FlexMainView(user);
        FlexBody newBody = new FlexBody(user);
        mainView.replaceBody(newBody);
        assertEquals(newBody, mainView.getBody());
    }

    /**
     * Test of getUser method, of class FlexMainView.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMainView mainView = new FlexMainView(user);
        assertNotNull(mainView.getUser());
    }

    /**
     * Test of populate method, of class FlexMainView.
     */
    @Test
    public void testPopulate() {
        System.out.println("populate");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMainView mainView = new FlexMainView(user);
        mainView.populate();
    }
    
}