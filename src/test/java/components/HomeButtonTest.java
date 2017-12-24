/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import components.HomeButton;
import components.FlexCaptions;
import components.FlexStyleNames;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class test the structure of CancelButton.
 *
 * @author zua
 */
public class HomeButtonTest {

    public HomeButtonTest() {
    }

    @Test
    public void testContructor() {
        HomeButton button = new HomeButton();
        assertEquals(FlexCaptions.HOME, button.getCaption());
    }
    
    @Test
    public void testClick() {
        HomeButton home = new HomeButton();
        home.click();
    }
}
