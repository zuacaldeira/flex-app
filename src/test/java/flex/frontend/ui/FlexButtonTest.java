/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class FlexButtonTest {
    
    public FlexButtonTest() {
    }

    @DataProvider
    public static Object[][] buttonsProvider() {
        return new Object[][] {
                {new FlexButton(),                   null, null},
                {new FlexButton("caption"),          "caption", null},
                {new FlexButton(VaadinIcons.ABACUS),  null, VaadinIcons.ABACUS},
                {new FlexButton("caption", VaadinIcons.ABACUS), "caption", VaadinIcons.ABACUS},
                {new FlexButton("caption", null), "caption", null},
                {new FlexButton(null, VaadinIcons.ABACUS), null, VaadinIcons.ABACUS},
                {new FlexButton(null, null), null, null}
        };
    }
    @Test
    @UseDataProvider("buttonsProvider")
    public void testContructors(FlexButton button, String expectedCaption, Resource expectedIcon) {
        assertEquals(expectedCaption, button.getDescription());
        assertEquals(expectedIcon, button.getIcon());
    }
    
}
