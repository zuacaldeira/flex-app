/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

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
        //assertTrue(button.getCaption().contains(FlexCaptions.LOGOUT));
    }
}
