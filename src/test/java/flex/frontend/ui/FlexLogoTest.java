/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.icons.VaadinIcons;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class FlexLogoTest {
    
    public FlexLogoTest() {
    }

    @Test
    public void testIcon() {
        FlexLogo logo = new FlexLogo();
        assertTrue(logo.getValue().contains(VaadinIcons.MEGAFONE.getHtml()));
    }
    
}
