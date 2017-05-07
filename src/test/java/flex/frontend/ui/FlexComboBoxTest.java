/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class FlexComboBoxTest {
    
    public FlexComboBoxTest() {
    }

    @DataProvider
    public static Object[][] comboProvider() {
        List<String> options = new LinkedList<>();
        return new Object[][] {
                {new FlexComboBox<>()                  , null     , null},
                {new FlexComboBox<>("caption")         , "caption", null},
                {new FlexComboBox<>("caption", options), "caption", options},
        };
    }
    
    @Test
    @UseDataProvider("comboProvider")
    public void testContructors(FlexComboBox<String> combo, String expectedCaption, List<String> expectedOptions) {
        assertEquals(expectedCaption, combo.getCaption());
    }
    
}
