/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 *
 * @author zua
 */
public class FlexFooter extends HorizontalLayout {
    
    private Label copyright;
    
    public FlexFooter() {
        copyright = new Label(VaadinIcons.COPYRIGHT.getHtml() + " Pirigrino"
                + " <peregrino.do.piri@gmail.com>", ContentMode.HTML);
        copyright.setSizeUndefined();
        addComponent(copyright);
        setSizeFull();
        setSpacing(false);
        setStyleName("flex-menu");
        setComponentAlignment(copyright, Alignment.MIDDLE_CENTER);
    }
}
