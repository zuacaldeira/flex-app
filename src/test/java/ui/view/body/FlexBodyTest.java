/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.body;

import data.DataProviderType;
import db.FlexUser;
import db.NewsArticle;
import factory.ArticleView;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class FlexBodyTest {
    
    private static final String TEST_USERNAME = "test:username";
    private static final String TEST_PASSWORD = "test:password";
    
    public FlexBodyTest() {
    }

    /**
     * Test of getContent method, of class FlexBody.
     */
    @Test
    public void testGetContent() {
        System.out.println("getContent");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexBody instance = new FlexBody(user);
        assertNotNull(instance.getContent());
    }

    /**
     * Test of getMasterDetail method, of class FlexBody.
     */
    @Test
    public void testGetMasterDetail() {
        System.out.println("getMasterDetail");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexBody instance = new FlexBody(user);
        assertNotNull(instance.getMasterDetail());
    }

    /**
     * Test of addItemView method, of class FlexBody.
     */
    @Test
    public void testAddItemView() {
        System.out.println("addItemView");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexBody instance = new FlexBody(user);
        instance.addItemView(new NewsArticle());
    }

    /**
     * Test of getUser method, of class FlexBody.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexBody instance = new FlexBody(user);
        assertNotNull(instance.getUser());
    }

    /**
     * Test of updateData method, of class FlexBody.
     */
    @Test
    public void testUpdateData() {
        System.out.println("updateData");
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexBody instance = new FlexBody(user);
        instance.initBodyUpdaterThread(DataProviderType.FAKE, null);
    }
    
}
