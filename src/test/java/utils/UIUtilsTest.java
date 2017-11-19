/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import org.junit.Test;
import static org.junit.Assert.*;

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


}
