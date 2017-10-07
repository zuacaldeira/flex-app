/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.menu;

import data.DataProviderType;
import db.FlexUser;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.FlexLogger;
import utils.ServiceNotFoundException;

/**
 *
 * @author zua
 */
public class FlexMenuBarTest {

    private static final String TEST_USERNAME = "test:username";
    private static final String TEST_PASSWORD = "test:password";
    private FlexLogger logger;

    public FlexMenuBarTest() {
        logger = new FlexLogger(getClass());
        
    }

    /**
     * Test of initMenuItems method, of class FlexMenuBar.
     */
    @Test
    public void testInitMenuBar() {
        System.out.println("initMenuBar");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMenuBar menuBar = new FlexMenuBar(user);
        assertNull(menuBar.getHome());
        assertNull(menuBar.getNews());
        assertNull(menuBar.getPublishers());
        assertNull(menuBar.getCategories());
        assertNull(menuBar.getCountries());
        assertNull(menuBar.getLanguages());
        assertNull(menuBar.getLogout());
        menuBar.initMenuItems();
        assertNotNull(menuBar.getHome());
        assertNotNull(menuBar.getNews());
        assertNotNull(menuBar.getPublishers());
        assertNotNull(menuBar.getCategories());
        assertNotNull(menuBar.getCountries());
        assertNotNull(menuBar.getLanguages());
        assertNotNull(menuBar.getLogout());
    }

    /**
     * Test of populate method, of class FlexMenuBar.
     */
    @Test
    public void testPopulate() {
        System.out.println("populate");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMenuBar menuBar = new FlexMenuBar(user);
        assertNull(menuBar.getPublishers());
        assertNull(menuBar.getLanguages());
        assertNull(menuBar.getCountries());
        menuBar.initMenuItems();
        assertNotNull(menuBar.getPublishers());
        assertNotNull(menuBar.getLanguages());
        assertNotNull(menuBar.getCountries());
    }

    /**
     * Test of populateNewsCategory method, of class FlexMenuBar.
     */
    @Test(expected = ServiceNotFoundException.class)
    public void testPopulateNewsCategory() {
        System.out.println("updateNewsCategory");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMenuBar menuBar = new FlexMenuBar(user);
        menuBar.populateNewsCategory();
        
    }

    /**
     * Test of populateNewsPublisher method, of class FlexMenuBar.
     */
    @Test(expected = ServiceNotFoundException.class)
    public void testPopulateNewsPublisher() {
        System.out.println("updateNewsPublisher");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMenuBar menuBar = new FlexMenuBar(user);
        menuBar.populateNewsPublisher();
    }

    /**
     * Test of populateNewsLanguages method, of class FlexMenuBar.
     */
    @Test(expected = ServiceNotFoundException.class)
    public void testUpdateNewsLanguages() {
        System.out.println("updateNewsLanguages");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMenuBar menuBar = new FlexMenuBar(user);
        menuBar.populateNewsLanguages();
    }

    /**
     * Test of populateNewsCountries method, of class FlexMenuBar.
     */
    @Test(expected = ServiceNotFoundException.class)
    public void testUpdateNewsCountries() {
        System.out.println("updateNewsCountries");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMenuBar menuBar = new FlexMenuBar(user);
        menuBar.populateNewsCountries();
    }

    /**
     * Test of getUI method, of class FlexMenuBar.
     */
    @Test
    public void testGetUI() {
        System.out.println("getUI");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMenuBar menuBar = new FlexMenuBar(user);
        assertNull(menuBar.getUI());
    }

    /**
     * Test of getDataProviderType method, of class FlexMenuBar.
     */
    @Test(expected = ServiceNotFoundException.class)
    public void testGetDataProviderType() {
        System.out.println("getDataProviderType");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMenuBar menuBar = new FlexMenuBar(user);
        menuBar.initMenuItems();
        menuBar.populate();
        assertEquals(DataProviderType.CATEGORY, menuBar.getDataProviderType(menuBar.getCategories()));
    }

}
