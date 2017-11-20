/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import components.FlexButton;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import com.vaadin.icons.VaadinIcons;
import static org.junit.Assert.assertNotNull;
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
    @Test
    @UseDataProvider("buttons")
    public void test(FlexButton button) {
        assertNotNull(button);
    }
}
