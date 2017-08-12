/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.icons.VaadinIcons;
import db.news.FlexUser;
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
        FlexLogo logo = new FlexLogo(new FlexUser("alex", null));
        //assertEquals(VaadinIcons.MEGAFONE, logo.getIcon());
    }
    
}
