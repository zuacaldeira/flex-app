/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.common;

import org.ngutu.ui.common.HideButton;
import org.ngutu.ui.common.FlexCaptions;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import org.testng.annotations.Test;


/**
 *
 * @author zua
 */
public class HideButtonTest {
    
    public HideButtonTest() {
    }

    @Test
    public void testContructor() {
        HideButton button = new HideButton();
        assertEquals(FlexCaptions.HIDE, button.getDescription());
        assertNull(button.getCaption());
    }
    
}
