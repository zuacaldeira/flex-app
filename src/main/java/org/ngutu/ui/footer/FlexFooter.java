/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.footer;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.UI;
import db.auth.FlexUser;

/**
 *
 * @author zua
 */
public class FlexFooter extends HorizontalLayout {

    private static final long serialVersionUID = 3270583482855386420L;

    private FlexUser user;

    public FlexFooter() {
        initUser();
        HorizontalLayout left = new HorizontalLayout(getCopyright(), getPrivacy());
        HorizontalLayout middle = new HorizontalLayout(new Label("Developed by"), getDeveloper());
        HorizontalLayout right = new HorizontalLayout(new Label("Thank you!"), getPoweredBy());
        super.addComponents(
                left,
                middle,
                right);
        super.setSizeFull();
        super.setHeight("40px");
        super.setSpacing(false);
        super.setMargin(new MarginInfo(false, true));
        super.setComponentAlignment(left, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(middle, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(right, Alignment.MIDDLE_RIGHT);
        super.setStyleName("flex-footer");
    }

    private void initUser() {
        if (UI.getCurrent() != null) {
            System.out.println("Found USER -> " + UI.getCurrent().getSession().getAttribute("user"));
            user = (FlexUser) UI.getCurrent().getSession().getAttribute("user");
            System.out.println("NEWS VIEW USER -> " + user);
        }
    }

    public FlexUser getUser() {
        return user;
    }

    private Component getDeveloper() {
        return new Link("Pirigrino", new ExternalResource("mailto:peregrino.do.piri@gmail.com"));
    }

    private Component getPoweredBy() {
        Link vaadinLink = new Link("Vaadin", new ExternalResource("https://vaadin.com"));
        vaadinLink.setTargetName("_blank");
        
        Link neo4jLink = new Link("Neo4j", new ExternalResource("https://neo4j.com"));
        neo4jLink.setTargetName("_blank");

        Link javaEELink = new Link("Java EE", new ExternalResource("http://www.oracle.com/technetwork/java/javaee/overview/index.html"));
        javaEELink.setTargetName("_blank");

        Link herokuLink = new Link("Heroku", new ExternalResource("https://heroku.com"));
        herokuLink.setTargetName("_blank");

        return new HorizontalLayout(
                vaadinLink, new Label("|"), neo4jLink, new Label("|"), javaEELink, new Label("|"), herokuLink);
    }

    private Component getPrivacy() {
        Link impressumLink = new Link("Impressum", new ThemeResource("html/impressum.html"));
        impressumLink.setTargetName("_blank");
        
        Link privacyLink = new Link("Privacy", new ThemeResource("html/privacy.html"));
        privacyLink.setTargetName("_blank");

        Link termsLink = new Link("Terms and Conditions", new ThemeResource("html/terms.html"));
        termsLink.setTargetName("_blank");

        return new HorizontalLayout(
                impressumLink, new Label("|"), privacyLink, new Label("|"), termsLink);
    }

    private Component getCopyright() {
        String text = VaadinIcons.COPYRIGHT.getHtml() + " 2017 ngutu.org";
        return new Label(text, ContentMode.HTML);
    }

}
