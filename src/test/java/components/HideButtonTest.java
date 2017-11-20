/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import components.FlexStyleNames;
import components.HideButton;
import components.FlexCaptions;
import org.junit.Test;
import static org.junit.Assert.*;

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
        assertTrue(button.getStyleName().contains(FlexStyleNames.FLEX_BUTTON));
        assertTrue(button.getStyleName().contains(FlexStyleNames.FLEX_ACTION));
        assertEquals(FlexCaptions.HIDE, button.getDescription());
        assertNull(button.getCaption());
    }
    
}
