/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.icons.VaadinIcons;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class CancelButtonTest {
    
    public CancelButtonTest() {
    }

    @Test
    public void testSomeMethod() {
        CancelButton button = new CancelButton();
        assertTrue(button instanceof FlexButton);
        assertNotNull(button.getIcon()); //checkIcon();
        assertEquals(VaadinIcons.TIME_BACKWARD, button.getIcon()); //checkIcon();
    }
    
}
