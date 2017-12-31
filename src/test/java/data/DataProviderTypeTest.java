/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;


/**
 *
 * @author zua
 */
public class DataProviderTypeTest {
    
    public DataProviderTypeTest() {
    }

    /**
     * Test of values method, of class DataProviderType.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        assertTrue(DataProviderType.values().length > 0);
    }

    /**
     * Test of valueOf method, of class DataProviderType.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        assertEquals(DataProviderType.CATEGORY, DataProviderType.valueOf("CATEGORY"));
    }
    
}
