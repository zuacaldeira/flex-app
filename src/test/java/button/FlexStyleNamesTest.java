/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package button;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class FlexStyleNamesTest {
    
    public FlexStyleNamesTest() {
    }

    @Test
    public void testNew() {
        assertNotNull(new FlexStyleNames());
        assertEquals("flex-action", FlexStyleNames.FLEX_ACTION);
        assertEquals("flex-button", FlexStyleNames.FLEX_BUTTON);
    }
    
}
