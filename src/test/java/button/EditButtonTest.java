/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package button;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author zua
 */
public class EditButtonTest {

    public EditButtonTest() {
    }

    @Test
    public void testNew() {
        EditButton button = new EditButton();
        assertTrue(button.getStyleName().contains("flex-action"));
    }

}
