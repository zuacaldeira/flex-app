/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import components.FlexCaptions;
import components.SaveButton;
import components.FlexStyleNames;
import com.vaadin.ui.themes.ValoTheme;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class test the structure of CancelButton.
 *
 * @author zua
 */
public class SaveButtonTest {

    public SaveButtonTest() {
    }

    @Test
    public void testContructor() {
        SaveButton button = new SaveButton();
        assertTrue(button.getStyleName().contains(FlexStyleNames.FLEX_BUTTON));
        assertEquals(FlexCaptions.SAVE, button.getCaption());
    }

}
