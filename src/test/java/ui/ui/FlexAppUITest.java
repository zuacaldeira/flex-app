/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.ui;

import ui.ui.FlexAppUI;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class FlexAppUITest {
    
    public FlexAppUITest() {
    }

    /**
     * Test of getPageLocation method, of class FlexAppUI.
     */
    @Test
    public void testGetPageLocation() {
        System.out.println("getPageLocation");
        FlexAppUI instance = new FlexAppUI();
        assertNull(instance.getPageLocation());
    }

    /**
     * Test of getContent method, of class FlexAppUI.
     */
    @Test
    public void testGetContent() {
        System.out.println("getContent");
        FlexAppUI instance = new FlexAppUI();
        assertNull(instance.getContent());
    }
    
    @Test
    public void testInit() {
        System.out.println("getContent");
        FlexAppUI instance = new FlexAppUI();
        instance.init(null);        
    }
    
}
