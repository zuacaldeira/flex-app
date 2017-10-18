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
import com.vaadin.ui.Label;
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
        super.setComponentAlignment(copyright, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(poweredBy, Alignment.MIDDLE_RIGHT);        
    }

    public Link getCopyright() {
        return copyright;
    }

    public FlexUser getUser() {
        return user;
    }

    private void initCopyright() {
        copyright = new Link("Pirigrino", new ExternalResource("mailto:peregrino.do.piri@gmail.com"));
        copyright.setIcon(VaadinIcons.COPYRIGHT);
        copyright.setTargetName("blank");
        copyright.setSizeUndefined();
    }

    private void initPoweredBy() {
        poweredBy = new HorizontalLayout();
        poweredBy.addComponents(new Label("Powered by:"), getVaadinLink(), getNeo4jLink());
        poweredBy.setHeight("100%");
        poweredBy.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
    }

    private Link getVaadinLink() {
        Link link =  new Link("Vaadin", new ExternalResource("https://vaadin.com"));
        
        return link;
    }

    private Link getNeo4jLink() {
        return new Link("Neo4j", new ExternalResource("https://neo4j.com"));
    }

}
