/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import db.FlexUser;
import db.NewsSource;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.FlexAppLogger;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class NewsMenuBarTest {

    private static final String TEST_USERNAME = "test:username";
    private static final String TEST_PASSWORD = "test:password";
    private FlexAppLogger logger;

    public NewsMenuBarTest() {
        logger = new FlexAppLogger(getClass());

    }

    /**
     * Test of initMenuItems method, of class NewsMenuBar.
     */
    @Test
    public void testInitMenuBar() {
        System.out.println("initMenuBar");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsMenuBar menuBar = new NewsMenuBar(user);
        assertNull(menuBar.getNews());
        assertNull(menuBar.getPublishers());
        assertNull(menuBar.getCategories());
        assertNull(menuBar.getCountries());
        assertNull(menuBar.getLanguages());
    }

    /**
     * Test of populate method, of class NewsMenuBar.
     */
    @Test
    public void testPopulate() {
        System.out.println("populate");
        initMinimalScenario();
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsMenuBar menuBar = new NewsMenuBar(user);
        assertNull(menuBar.getPublishers());
        assertNull(menuBar.getLanguages());
        assertNull(menuBar.getCountries());
        //menuBar.populate();
    }

    /**
     * Test of populateNewsCategory method, of class NewsMenuBar.
     */
    @Test
    public void testPopulateNewsCategory() {
        System.out.println("updateNewsCategory");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsMenuBar menuBar = new NewsMenuBar(user);
        menuBar.populateNewsCategory();

    }

    /**
     * Test of populateNewsPublisher method, of class NewsMenuBar.
     */
    @Test
    public void testPopulateNewsPublisher() {
        System.out.println("updateNewsPublisher");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsMenuBar menuBar = new NewsMenuBar(user);
        menuBar.populateNewsPublisher();
    }

    /**
     * Test of populateNewsLanguages method, of class NewsMenuBar.
     */
    @Test
    public void testUpdateNewsLanguages() {
        System.out.println("updateNewsLanguages");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsMenuBar menuBar = new NewsMenuBar(user);
        menuBar.populateNewsLanguages();
    }

    /**
     * Test of populateNewsCountries method, of class NewsMenuBar.
     */
    @Test
    public void testUpdateNewsCountries() {
        System.out.println("updateNewsCountries");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsMenuBar menuBar = new NewsMenuBar(user);
        menuBar.populateNewsCountries();
    }

    /**
     * Test of getUI method, of class NewsMenuBar.
     */
    @Test
    public void testGetUI() {
        System.out.println("getUI");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsMenuBar menuBar = new NewsMenuBar(user);
        assertNull(menuBar.getUI());
    }

    private void initMinimalScenario() {
        NewsSource source = new NewsSource("sourceId", "Publishing AG", "Source Description", "url", "business", "en", "GB");
        ServiceLocator.getInstance().findSourcesService().save(source);
    }

    @Test
    public void testSelectOther() {
        System.out.println("selectOther");
        initMinimalScenario();
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsView view = new NewsView();
        view.populate();

        if (view.getMenu() != null) {
            NewsMenuBar menuBar = view.getMenu().getMenuBar();
            MenuItem menuItem = menuBar.getLatest();
            if (menuItem != null) {
                MenuBar.Command command = menuItem.getCommand();
                command.menuSelected(menuItem);
            }
        }
    }

}
