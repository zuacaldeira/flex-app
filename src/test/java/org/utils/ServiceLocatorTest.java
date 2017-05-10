/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utils;

import flex.frontend.ui.news.UITest;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class ServiceLocatorTest extends UITest {
    
    public ServiceLocatorTest() {
    }

    /**
     * Test of findArticlesService method, of class ServiceLocator.
     */
    @Test
    public void testFindNewsArticleService() {
        System.out.println("findNewsArticleService");
        assertNotNull(ServiceLocator.getInstance().findArticlesService());
    }

    /**
     * Test of findSourcesService method, of class ServiceLocator.
     */
    @Test
    public void testFindNewsSourceService() {
        System.out.println("findNewsSourceService");
        assertNotNull(ServiceLocator.getInstance().findSourcesService());
    }

    /**
     * Test of findAuthorsService method, of class ServiceLocator.
     */
    @Test
    public void testFindAuthorsService() {
        System.out.println("findAuthorsService");
        assertNotNull(ServiceLocator.getInstance().findAuthorsService());
    }
    
}
