/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.common;

import org.ngutu.ui.common.FlexWindow;
import com.vaadin.ui.Label;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.Test;

/**
 *
 * @author zua
 */
public class FlexWindowTest {
    
    public FlexWindowTest() {
    }

    @Test
    public void testSomeMethod() {
        FlexWindow flexWindow = new FlexWindow("Test Caption", new Label("Hello"));
        assertNotNull(flexWindow.getContent());
    }
    
}
