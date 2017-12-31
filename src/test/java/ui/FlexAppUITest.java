/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import static org.testng.Assert.assertNull;
import org.testng.annotations.Test;

/**
 *
 * @author zua
 */
public class FlexAppUITest {
    
    public FlexAppUITest() {
    }

    /**
     * Test of getContent method, of class NgutuUI.
     */
    @Test
    public void testGetContent() {
        System.out.println("getContent");
        NgutuUI instance = new NgutuUI();
        assertNull(instance.getContent());
    }
    
    @Test
    public void testInit() {
        System.out.println("getContent");
        NgutuUI instance = new NgutuUI();
        instance.init(null);        
    }
    
}
