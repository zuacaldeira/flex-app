/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.main;

import db.FlexUser;
import org.junit.Test;
import static org.junit.Assert.*;
import org.ngutu.ui.news.NewsBody;
import view.footer.FlexFooter;
import org.ngutu.ui.news.NewsView;
import org.ngutu.ui.news.NewsMenu;

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
        NewsView mainView = new NewsView();
        mainView.enter(null);
        assertNotNull(mainView.getMenu());
        assertNotNull(mainView.getBody());
        assertNotNull(mainView.getFooter());
    }

    /**
     * Test of setMenu method, of class NewsView.
     */
    @Test
    public void testSetMenu() {
        System.out.println("setMenu");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsView mainView = new NewsView();
        NewsMenu menu = new NewsMenu();
        mainView.setMenu(menu);
        assertEquals(menu, mainView.getMenu());
    }

    /**
     * Test of setFooter method, of class NewsView.
     */
    @Test
    public void testSetFooter() {
        System.out.println("setFooter");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsView mainView = new NewsView();
        FlexFooter footer = new FlexFooter();
        mainView.setFooter(footer);
        assertEquals(footer, mainView.getFooter());
    }

    /**
     * Test of getMenu method, of class NewsView.
     */
    @Test
    public void testGetMenu() {
        System.out.println("getMenu");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsView mainView = new NewsView();
        NewsMenu menu = new NewsMenu();
        mainView.setMenu(menu);
        assertEquals(menu, mainView.getMenu());
    }

    /**
     * Test of getBody method, of class NewsView.
     */
    @Test
    public void testGetBody() {
        System.out.println("getBody");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsView mainView = new NewsView();
        mainView.enter(null);
        assertNotNull(mainView.getBody());
    }

    /**
     * Test of getFooter method, of class NewsView.
     */
    @Test
    public void testGetFooter() {
        System.out.println("getFooter");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsView mainView = new NewsView();
        mainView.enter(null);
        assertNotNull(mainView.getFooter());
    }

    /**
     * Test of replaceBody method, of class NewsView.
     */
    @Test
    public void testReplaceBody() {
        System.out.println("replaceBody");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsView mainView = new NewsView();
        mainView.enter(null);
        NewsBody newBody = new NewsBody();
        mainView.replaceBody(newBody);
        assertEquals(newBody, mainView.getBody());
    }

    /**
     * Test of getUser method, of class NewsView.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsView mainView = new NewsView();
    }

    /**
     * Test of populate method, of class NewsView.
     */
    @Test
    public void testPopulate() {
        System.out.println("populate");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsView mainView = new NewsView();
    }
    
}
