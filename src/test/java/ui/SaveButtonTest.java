/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import ui.FlexButton;
import ui.SaveButton;
import com.vaadin.icons.VaadinIcons;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class SaveButtonTest {
    
    public SaveButtonTest() {
    }

    @Test
    public void testSomeMethod() {
        SaveButton button = new SaveButton();
        assertTrue(button instanceof FlexButton);
        assertNotNull(button.getIcon()); //checkIcon();
        assertEquals(VaadinIcons.CHECK, button.getIcon()); //checkIcon();
    }
    
}
