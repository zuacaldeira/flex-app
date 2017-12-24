/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

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
        assertEquals(FlexCaptions.DELETE, button.getCaption());
    }

}
