/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import components.FlexStyleNames;
import components.FlexCaptions;
import components.DeleteButton;
import com.vaadin.ui.themes.ValoTheme;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class DeleteButtonTest {

    public DeleteButtonTest() {
    }

    @Test
    public void testContructor() {
        DeleteButton button = new DeleteButton();
        assertTrue(button.getStyleName().contains(FlexStyleNames.FLEX_BUTTON));
        assertTrue(button.getStyleName().contains(ValoTheme.BUTTON_DANGER));
        assertEquals(FlexCaptions.DELETE, button.getCaption());
        assertTrue(button.getDescription().isEmpty());
    }

}
