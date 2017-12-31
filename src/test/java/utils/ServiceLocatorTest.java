/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import static org.testng.Assert.assertNotNull;
import org.testng.annotations.Test;


/**
 *
 * @author zua
 */
public class ServiceLocatorTest {

    public ServiceLocatorTest() {
    }

    /**
     * Test of findArticlesService method, of class ServiceLocator.
     */
    @Test
    public void testFindArticlesService() {
        System.out.println("findArticlesService");
        ServiceLocator locator = ServiceLocator.getInstance();
        assertNotNull(locator.findArticlesService());
    }

    /**
     * Test of findSourcesService method, of class ServiceLocator.
     */
    @Test
    public void testFindSourcesService() {
        System.out.println("findSourcesService");

        ServiceLocator locator = ServiceLocator.getInstance();
        assertNotNull(locator.findSourcesService());
    }

    /**
     * Test of findAuthorsService method, of class ServiceLocator.
     */
    @Test
    public void testFindAuthorsService() {
        System.out.println("findAuthorsService");
        ServiceLocator locator = ServiceLocator.getInstance();
        assertNotNull(locator.findAuthorsService());
    }

    /**
     * Test of findUserService method, of class ServiceLocator.
     */
    @Test
    public void testFindUserService() {
        System.out.println("findUserService");
        ServiceLocator locator = ServiceLocator.getInstance();
        assertNotNull(locator.findAuthorsService());
    }

}
