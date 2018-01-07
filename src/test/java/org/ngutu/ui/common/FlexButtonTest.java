/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.common;

import org.ngutu.ui.common.FlexButton;
import com.vaadin.icons.VaadinIcons;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author zua
 */
public class FlexButtonTest {
    
    public FlexButtonTest() {
    }

    @DataProvider
    public static Object[][] buttons() {
        return new Object[][] {
            {new FlexButton()},
            {new FlexButton("A Caption")},
            {new FlexButton(VaadinIcons.ABACUS)},
            {new FlexButton("A Caption", VaadinIcons.ABACUS)}
        };
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test(dataProvider = "buttons")
    public void test(FlexButton button) {
        assertNotNull(button);
    }
}
