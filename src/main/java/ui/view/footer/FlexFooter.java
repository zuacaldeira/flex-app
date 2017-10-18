/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.footer;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Link;
import db.FlexUser;

/**
 *
 * @author zua
 */
public class FlexFooter extends HorizontalLayout {

    private final FlexUser user;
    private Link copyright;
    private HorizontalLayout poweredBy;

    public FlexFooter(FlexUser user) {
        this.user = user;
        initCopyright();
        initPoweredBy();
        super.addComponents(copyright, poweredBy);
        super.setSizeFull();
        super.setSpacing(false);
        super.setStyleName("flex-footer");
        super.setComponentAlignment(copyright, Alignment.MIDDLE_CENTER);
    }

    public Link getCopyright() {
        return copyright;
    }

    public FlexUser getUser() {
        return user;
    }

    private void initCopyright() {
        copyright = new Link(" Pirigrino", new ExternalResource("mailto:peregrino.do.piri@gmail.com"));
        copyright.setIcon(VaadinIcons.COPYRIGHT);
        copyright.setTargetName("blank");
        copyright.setSizeUndefined();
    }

    private void initPoweredBy() {
        poweredBy = new HorizontalLayout();
        poweredBy.setCaption("Powered By");
        poweredBy.addComponents(getVaadinLink(), getNeo4jLink());
    }

    private Link getVaadinLink() {
        return new Link("Vaadin", new ExternalResource("https://vaadin.com"));
    }

    private Link getNeo4jLink() {
        return new Link("Neo4j", new ExternalResource("https://neo4j.com"));
    }

}
