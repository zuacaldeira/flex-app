/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.common;

import org.ngutu.ui.common.FlexCaptions;
import org.ngutu.ui.common.DeleteButton;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;


/**
 *
 * @author zua
 */
public class DeleteButtonTest {

    public DeleteButtonTest() {
    }

    @Test
    public void testContructor() {
        DeleteButton button = new DeleteButton();
        assertEquals(FlexCaptions.DELETE, button.getCaption());
    }

}
