/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utils;

import javax.naming.NamingException;
import org.junit.Test;
import static org.junit.Assert.*;

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
    public void testFindNewsArticleService() throws NamingException {
        System.out.println("findNewsArticleService");
        MockInitialContext mockContext = new MockInitialContext();
        ServiceLocator.setInitialContext(mockContext);
        assertNotNull(ServiceLocator.findArticlesService());
    }

    /**
     * Test of findSourcesService method, of class ServiceLocator.
     */
    @Test
    public void testFindNewsSourceService() throws NamingException {
        System.out.println("findNewsSourceService");
        MockInitialContext mockContext = new MockInitialContext();
        ServiceLocator.setInitialContext(mockContext);
        assertNotNull(ServiceLocator.findSourcesService());
    }

    /**
     * Test of findAuthorsService method, of class ServiceLocator.
     */
    @Test
    public void testFindAuthorsService() throws NamingException {
        System.out.println("findAuthorsService");
        MockInitialContext mockContext = new MockInitialContext();
        ServiceLocator.setInitialContext(mockContext);
        assertNotNull(ServiceLocator.findAuthorsService());
    }
    
}
