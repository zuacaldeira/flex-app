/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package button;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class FakeButtonTest {
    
    public FakeButtonTest() {
    }

    @Test
    public void testContructor() {
        FakeButton button = new FakeButton();
        assertTrue(button.getStyleName().contains(FlexStyleNames.FLEX_BUTTON));
        assertTrue(button.getStyleName().contains(FlexStyleNames.FLEX_ACTION));
        assertEquals(FlexCaptions.FAKE, button.getDescription());
        assertNull(button.getCaption());
    }
    
}
