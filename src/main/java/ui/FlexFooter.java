/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import db.news.FlexUser;

/**
 *
 * @author zua
 */
public class FlexFooter extends HorizontalLayout {
    
    private final Label copyright;
    private FlexUser user;
    
    public FlexFooter(FlexUser user) {
        copyright = new Label(VaadinIcons.COPYRIGHT.getHtml() + " Pirigrino"
                + " <peregrino.do.piri@gmail.com>", ContentMode.HTML);
        copyright.setSizeUndefined();
        super.addComponent(copyright);
        super.setSizeFull();
        super.setSpacing(false);
        super.setStyleName("flex-footer");
        super.setComponentAlignment(copyright, Alignment.MIDDLE_CENTER);
    }
}
