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
public class LoginButtonTest {

    public LoginButtonTest() {
    }

    @Test
    public void testContructor() {
        SaveButton button = new SaveButton();
        assertEquals(FlexCaptions.LOGIN, button.getCaption());
    }

}
