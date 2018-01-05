/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import db.auth.FlexUser;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import org.testng.annotations.Test;

/**
 *
 * @author zua
 */
public class NewsBodyTest {

    private static final String TEST_USERNAME = "test:username";
    private static final String TEST_PASSWORD = "test:password";

    public NewsBodyTest() {
    }

    /**
     * Test of getContent method, of class NewsBody.
     */
    @Test
    public void testGetContent() {
        System.out.println("getContent");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsBody instance = new NewsBody();
        assertNotNull(instance.getContent());
        assertNotNull(instance.getMasterDetail());
    }

    /**
     * Test of getMasterDetail method, of class NewsBody.
     */
    @Test
    public void testGetMasterDetail() {
        System.out.println("getMasterDetail");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsBody instance = new NewsBody();
        assertNotNull(instance.getMasterDetail());
    }

    /**
     * Test of addItemView method, of class NewsBody.
     */
    @Test
    public void testAddItemView() {
        System.out.println("addItemView");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsBody instance = new NewsBody();

        //instance.addItemView(new NewsArticle("TEST_USERNAME", "TEST_PASSWORD", "TEST_USERNAME", "TEST_USERNAME", new Date(), "TEST_USERNAME", "TEST_USERNAME", "TEST_USERNAME"));
    }

    /**
     * Test of getUser method, of class NewsBody.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsBody instance = new NewsBody();
        assertNull(instance.getUser());
    }

    /**
     * Test of updateData method, of class NewsBody.
     */
    @Test
    public void testUpdateData() {
        System.out.println("updateData");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        NewsBody instance = new NewsBody();
    }

}
