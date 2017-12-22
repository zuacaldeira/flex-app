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
