/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import flex.frontend.ui.news.UITest;
import org.junit.Test;

/**
 *
 * @author zua
 */
public abstract class MainViewTest extends UITest {
    
    public MainViewTest() {
    }

    /**
     * Test of setMenu method, of class MainView.
     */
    @Test
    public abstract void testSetMenu();

    /**
     * Test of setBody method, of class MainView.
     */
    @Test
    public abstract void testSetBody();

    /**
     * Test of setFooter method, of class MainView.
     */
    @Test
    public abstract void testSetFooter();
    
}
