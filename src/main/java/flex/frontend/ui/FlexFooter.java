/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 *
 * @author zua
 */
public class FlexFooter extends HorizontalLayout {
    
    private Label copyright;
    
    public FlexFooter() {
        copyright = new Label("Pirigrino");
        copyright.setIcon(VaadinIcons.COPYRIGHT);
        addComponent(copyright);
    }
}
