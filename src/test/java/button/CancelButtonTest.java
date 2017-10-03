/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package button;

import com.vaadin.ui.themes.ValoTheme;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class test the structure of CancelButton.
 *
 * @author zua
 */
public class CancelButtonTest {

    public CancelButtonTest() {
    }

    @Test
    public void testContructor() {
        CancelButton button = new CancelButton();
        assertTrue(button.getStyleName().contains(FlexStyleNames.FLEX_BUTTON));
        assertTrue(button.getStyleName().contains(ValoTheme.BUTTON_DANGER));
        assertEquals(FlexCaptions.CANCEL, button.getCaption());
    }

}
