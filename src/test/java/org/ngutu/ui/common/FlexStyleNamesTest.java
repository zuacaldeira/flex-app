/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.common;

import org.ngutu.ui.common.FlexStyleNames;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.Test;

/**
 *
 * @author zua
 */
public class FlexStyleNamesTest {
    
    public FlexStyleNamesTest() {
    }

    @Test
    public void testNew() {
        assertNotNull(new FlexStyleNames());
        assertEquals("flex-action", FlexStyleNames.FLEX_ACTION);
        assertEquals("flex-button", FlexStyleNames.FLEX_BUTTON);
    }
    
}