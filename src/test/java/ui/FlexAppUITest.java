/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import ui.NewsUI;
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
     * Test of getContent method, of class NewsUI.
     */
    @Test
    public void testGetContent() {
        System.out.println("getContent");
        NewsUI instance = new NewsUI();
        assertNull(instance.getContent());
    }
    
    @Test
    public void testInit() {
        System.out.println("getContent");
        NewsUI instance = new NewsUI();
        instance.init(null);        
    }
    
}
