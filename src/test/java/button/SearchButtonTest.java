/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package button;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class test the structure of CancelButton.
 *
 * @author zua
 */
public class SearchButtonTest {

    public SearchButtonTest() {
    }

    @Test
    public void testContructor() {
        SearchButton button = new SearchButton();
        assertTrue(button.getStyleName().contains(FlexStyleNames.FLEX_BUTTON));
        assertEquals(FlexCaptions.SEARCH, button.getCaption());
    }

}
