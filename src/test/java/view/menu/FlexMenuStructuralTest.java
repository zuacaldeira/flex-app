/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.menu;

import view.menu.NewsMenu;
import db.FlexUser;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author zua
 */
public class FlexMenuStructuralTest {

    private static final String TEST_USERNAME = "test:username";
    private static final String TEST_PASSWORD = "test:password";

    public FlexMenuStructuralTest() {
    }

    /**
     * Test of getUser method, of class NewsMenu.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsMenu menu = new NewsMenu(user);
        assertEquals(user, menu.getUser());
    }

    /**
     * Test of populate method, of class NewsMenu.
     */
    @Test
    @Ignore
    public void testPopulate() {
        System.out.println("populate");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsMenu menu = new NewsMenu(user);
        assertEquals(user, menu.getUser());
        assertNull(menu.getMenuBar());
        assertNull(menu.getLogo());        
        menu.populate();
        assertNotNull(menu.getMenuBar());
        assertNotNull(menu.getLogo());
    }

    /**
     * Test of getLogo method, of class NewsMenu.
     */
    @Test
    public void testGetLogo() {
        System.out.println("getLogo");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsMenu menu = new NewsMenu(user);
        assertNotNull(menu.getLogo());
    }

    /**
     * Test of getMenuBar method, of class NewsMenu.
     */
    @Test
    public void testGetMenuBar() {
        System.out.println("getMenuBar");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsMenu menu = new NewsMenu(user);
        assertNotNull(menu.getMenuBar());
    }

}