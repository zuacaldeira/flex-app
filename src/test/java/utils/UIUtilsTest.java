/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.vaadin.ui.Component;
import db.FlexUser;
import org.junit.Test;
import static org.junit.Assert.*;
import ui.view.main.FlexMainView;

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
        UIUtils result = UIUtils.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of getBody method, of class UIUtils.
     */
    @Test
    public void testGetBody() {
        System.out.println("getBody");
        UIUtils result = UIUtils.getInstance();
        FlexUser user = new FlexUser("test:username", "test:password");
        FlexMainView mainView = new FlexMainView(user);
        Component component = mainView.getBody().getContent();
        assertNotNull(result.getBody(component));
    }

    /**
     * Test of getMainView method, of class UIUtils.
     */
    @Test
    public void testGetMainView() {
        System.out.println("getMainView");
        UIUtils result = UIUtils.getInstance();
        FlexUser user = new FlexUser("test:username", "test:password");
        FlexMainView mainView = new FlexMainView(user);
        Component component = mainView.getBody().getContent();
        assertNotNull(result.getMainView(component));
    }

}
