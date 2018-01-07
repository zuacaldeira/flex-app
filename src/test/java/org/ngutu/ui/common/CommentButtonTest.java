/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.common;

import org.ngutu.ui.common.FlexCaptions;
import org.ngutu.ui.common.CommentButton;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import org.testng.annotations.Test;


/**
 *
 * @author zua
 */
public class CommentButtonTest {

    public CommentButtonTest() {
    }

    @Test
    public void testContructor() {
        CommentButton button = new CommentButton();
        assertEquals(FlexCaptions.COMMENT, button.getDescription());
        assertNull(button.getCaption());
    }

}
