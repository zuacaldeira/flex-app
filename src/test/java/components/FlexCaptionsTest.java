/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.Test;

/**
 *
 * @author zua
 */
public class FlexCaptionsTest {

    public FlexCaptionsTest() {
    }

    @Test
    public void testNew() {
        assertNotNull(new FlexCaptions());
        assertEquals("Edit", FlexCaptions.EDIT);
        assertEquals("Cancel", FlexCaptions.CANCEL);
        assertEquals("Comment", FlexCaptions.COMMENT);
        assertEquals("Delete", FlexCaptions.DELETE);
        assertEquals("Fake", FlexCaptions.FAKE);
        assertEquals("Favorite", FlexCaptions.FAVORITE);
        assertEquals("Hide", FlexCaptions.HIDE);
        assertEquals("Home", FlexCaptions.HOME);
        assertEquals("Logout", FlexCaptions.LOGOUT);
        assertEquals("Save", FlexCaptions.SAVE);
        assertEquals("Search", FlexCaptions.SEARCH);
    }

}
