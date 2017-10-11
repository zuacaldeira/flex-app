/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.menu;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import data.DataProviderType;
import db.FlexUser;
import db.NewsSource;
import org.junit.Test;
import static org.junit.Assert.*;
import ui.view.main.FlexMainView;
import utils.FlexLogger;
import utils.ServiceLocator;

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
        initMinimalScenario();
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMenuBar menuBar = new FlexMenuBar(user);
        menuBar.populate();
        assertNotNull(menuBar.getPublishers());
        assertNotNull(menuBar.getLanguages());
        assertNotNull(menuBar.getCountries());
    }

    /**
     * Test of populateNewsCategory method, of class FlexMenuBar.
     */
    @Test
    public void testPopulateNewsCategory() {
        System.out.println("updateNewsCategory");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMenuBar menuBar = new FlexMenuBar(user);
        menuBar.populateNewsCategory();

    }

    /**
     * Test of populateNewsPublisher method, of class FlexMenuBar.
     */
    @Test
    public void testPopulateNewsPublisher() {
        System.out.println("updateNewsPublisher");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMenuBar menuBar = new FlexMenuBar(user);
        menuBar.populateNewsPublisher();
    }

    /**
     * Test of populateNewsLanguages method, of class FlexMenuBar.
     */
    @Test
    public void testUpdateNewsLanguages() {
        System.out.println("updateNewsLanguages");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMenuBar menuBar = new FlexMenuBar(user);
        menuBar.populateNewsLanguages();
    }

    /**
     * Test of populateNewsCountries method, of class FlexMenuBar.
     */
    @Test
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
    @Test
    public void testGetDataProviderType() {
        System.out.println("getDataProviderType");
        initMinimalScenario();
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMenuBar menuBar = new FlexMenuBar(user);
        menuBar.populate();
        assertEquals(DataProviderType.HOME, menuBar.getDataProviderType(menuBar.getHome()));
        assertEquals(DataProviderType.LATEST, menuBar.getDataProviderType(menuBar.getLatest()));
        assertEquals(DataProviderType.OLDEST, menuBar.getDataProviderType(menuBar.getOldest()));
        assertEquals(DataProviderType.READ, menuBar.getDataProviderType(menuBar.getRead()));
        assertEquals(DataProviderType.FAVORITE, menuBar.getDataProviderType(menuBar.getFavorite()));
        assertEquals(DataProviderType.FAKE, menuBar.getDataProviderType(menuBar.getFake()));
        assertEquals(DataProviderType.FULL, menuBar.getDataProviderType(menuBar.getFull()));
        assertEquals(DataProviderType.IMAGES_ONLY, menuBar.getDataProviderType(menuBar.getImagesOnly()));
        assertEquals(DataProviderType.TITLES_ONLY, menuBar.getDataProviderType(menuBar.getTitlesOnly()));
        assertEquals(DataProviderType.PUBLISHER, menuBar.getDataProviderType(menuBar.getPublishers()));
        assertEquals(DataProviderType.CATEGORY, menuBar.getDataProviderType(menuBar.getCategories()));
        assertEquals(DataProviderType.LANGUAGES, menuBar.getDataProviderType(menuBar.getLanguages()));
        assertEquals(DataProviderType.COUNTRIES, menuBar.getDataProviderType(menuBar.getCountries()));
        assertEquals(DataProviderType.SEARCH, menuBar.getDataProviderType(menuBar.getSearch()));
        assertEquals(DataProviderType.LOGOUT, menuBar.getDataProviderType(menuBar.getLogout()));
    }

    private void initMinimalScenario() {
        NewsSource source = new NewsSource("sourceId", "Publishing AG", "Source Description", "url", "business", "en", "GB");
        ServiceLocator.getInstance().findSourcesService().save(source);
    }

    @Test
    public void testSelectHome() {
        System.out.println("selectHome");
        initMinimalScenario();
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMainView view = new FlexMainView(user);
        view.populate();

        FlexMenuBar menuBar = view.getMenu().getMenuBar();
        MenuItem menuItem = menuBar.getHome();
        MenuBar.Command command = menuItem.getCommand();
        command.menuSelected(menuItem);
    }

    @Test
    public void testSelectSearch() {
        System.out.println("selectSearch");
        initMinimalScenario();
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMainView view = new FlexMainView(user);
        view.populate();

        FlexMenuBar menuBar = view.getMenu().getMenuBar();
        MenuItem menuItem = menuBar.getSearch();
        MenuBar.Command command = menuItem.getCommand();
        command.menuSelected(menuItem);
    }

    @Test
    public void testSelectLogout() {
        System.out.println("selectLogout");
        initMinimalScenario();
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMainView view = new FlexMainView(user);
        view.populate();

        FlexMenuBar menuBar = view.getMenu().getMenuBar();
        MenuItem menuItem = menuBar.getLogout();
        MenuBar.Command command = menuItem.getCommand();
        command.menuSelected(menuItem);
    }

    @Test
    public void testSelectOther() {
        System.out.println("selectOther");
        initMinimalScenario();
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexMainView view = new FlexMainView(user);
        view.populate();

        FlexMenuBar menuBar = view.getMenu().getMenuBar();
        MenuItem menuItem = menuBar.getLatest();
        MenuBar.Command command = menuItem.getCommand();
        command.menuSelected(menuItem);
    }

}