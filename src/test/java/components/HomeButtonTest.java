/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

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
