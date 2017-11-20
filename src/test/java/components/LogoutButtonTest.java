/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import components.FlexStyleNames;
import components.FlexCaptions;
import components.LogoutButton;
import com.vaadin.ui.themes.ValoTheme;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class test the structure of CancelButton.
 *
 * @author zua
 */
public class LogoutButtonTest {

    public LogoutButtonTest() {
    }

    @Test
    public void testContructor() {
        LogoutButton button = new LogoutButton();
        assertTrue(button.getStyleName().contains(FlexStyleNames.FLEX_BUTTON));
        assertEquals(FlexCaptions.LOGOUT, button.getCaption());
    }

}
