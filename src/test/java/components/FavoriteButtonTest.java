/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import org.testng.annotations.Test;

/**
 *
 * @author zua
 */
public class FavoriteButtonTest {
    
    public FavoriteButtonTest() {
    }

    @Test
    public void testContructor() {
        FavoriteButton button = new FavoriteButton();
        assertEquals(FlexCaptions.FAVORITE, button.getDescription());
        assertNull(button.getCaption());
    }
    
}
