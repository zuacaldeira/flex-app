/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.news;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class CountComponentTest {
    
    public CountComponentTest() {
    }

    /**
     * Test of getLabel method, of class CountComponent.
     */
    @Test
    public void testGetLabel() {
        System.out.println("getLabel");
        CountComponent instance = new CountComponent("Source", 100);
        assertNotNull(instance.getLabel());
        assertEquals("Source", instance.getLabel().getValue());
    }

    /**
     * Test of getNumber method, of class CountComponent.
     */
    @Test
    public void testGetNumber() {
        System.out.println("getNumber");
        CountComponent instance = new CountComponent("Source", 100);
        assertNotNull(instance.getNumber());
        assertEquals(100L, Long.valueOf(instance.getNumber().getValue()).longValue());
    }
    
    @Test
    public void testStyleName() {
        CountComponent instance = new CountComponent("Source", 100);
        assertNotNull(instance.getLabel());
        assertNotNull(instance.getNumber());
    }
    
}
