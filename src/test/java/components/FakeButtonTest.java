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
public class FakeButtonTest {
    
    public FakeButtonTest() {
    }

    @Test
    public void testContructor() {
        FakeButton button = new FakeButton();
        assertEquals(FlexCaptions.FAKE, button.getDescription());
        assertNull(button.getCaption());
    }
    
}
