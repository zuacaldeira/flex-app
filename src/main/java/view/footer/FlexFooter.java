/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.footer;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import db.FlexUser;

/**
 *
 * @author zua
 */
public class FlexFooter extends HorizontalLayout {

    private final FlexUser user;

    public FlexFooter(FlexUser user) {
        this.user = user;
        HorizontalLayout links = new HorizontalLayout(new Label("Powered by"), getCopyrightLink());
        links.setSizeUndefined();
        super.addComponent(links);
        super.setSizeFull();
        super.setSpacing(false);
        super.setStyleName("flex-footer");
        super.setComponentAlignment(links, Alignment.MIDDLE_CENTER);
    }

    public FlexUser getUser() {
        return user;
    }

    private Link getCopyrightLink() {
        Link copyright = new Link("Pirigrino", new ExternalResource("mailto:peregrino.do.piri@gmail.com"));
        copyright.setIcon(VaadinIcons.COPYRIGHT);
        copyright.setTargetName("blank");
        copyright.setSizeUndefined();
        return copyright;
    }
}
