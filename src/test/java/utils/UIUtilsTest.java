/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import db.FlexUser;
import org.junit.Test;
import static org.junit.Assert.*;
import view.FlexMainView;

/**
 *
 * @author zua
 */
public class UIUtilsTest {
    
    public UIUtilsTest() {
    }

    /**
     * Test of getInstance method, of class UIUtils.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        assertTrue(UIUtils.getInstance() == UIUtils.getInstance());
    }

    /**
     * Test of getBody method, of class UIUtils.
     */
    @Test(expected = ServiceNotFoundException.class)
    public void testGetBody() {
        System.out.println("getBody");
        FlexUser user = new FlexUser("username", "password");
        FlexMainView main = new FlexMainView(user);
        assertNotNull(UIUtils.getInstance().getBody(main));
    }

    /**
     * Test of getMainView method, of class UIUtils.
     */
    @Test(expected = ServiceNotFoundException.class)
    public void testGetMainView() {
        System.out.println("getMainView");
        FlexUser user = new FlexUser("username", "password");
        FlexMainView main = new FlexMainView(user);
        assertNotNull(UIUtils.getInstance().getMainView(main.getBody()));
    }
    
}
