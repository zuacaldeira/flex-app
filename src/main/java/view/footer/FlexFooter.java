/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.footer;

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
import db.FlexUser;

/**
 *
 * @author zua
 */
public class FlexFooter extends HorizontalLayout {

    private static final long serialVersionUID = 3270583482855386420L;

    private final FlexUser user;

    public FlexFooter(FlexUser user) {
        this.user = user;
        HorizontalLayout left = new HorizontalLayout(getCopyright(), getTerms(), getPrivacy());
        HorizontalLayout middle = new HorizontalLayout(new Label("Developed by"), getDeveloper());
        HorizontalLayout right = new HorizontalLayout(new Label("Powered by"), getPoweredBy());
        super.addComponents(
                left,
                middle,
                right);
        super.setSizeFull();
        super.setSpacing(false);
        super.setMargin(new MarginInfo(false, true));
        super.setComponentAlignment(left, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(middle, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(right, Alignment.MIDDLE_RIGHT);
        super.setStyleName("flex-footer");
    }

    public FlexUser getUser() {
        return user;
    }

    private Component getDeveloper() {
        return new Link("Pirigrino", new ExternalResource("mailto:peregrino.do.piri@gmail.com"));
    }

    private Component getPoweredBy() {
        Link vaadinLink = new Link("Vaadin", new ExternalResource("https://vaadin.com"));
        Link neo4jLink = new Link("Neo4j", new ExternalResource("https://neo4j.com"));
        Link javaEELink = new Link("Java EE", new ExternalResource("http://www.oracle.com/technetwork/java/javaee/overview/index.html"));
        Link heorkuLink = new Link("Heroku", new ExternalResource("https://heroku.com"));
        return new HorizontalLayout(
                vaadinLink, new Label("|"), neo4jLink, new Label("|"), javaEELink, new Label("|"), heorkuLink);
    }

    private Component getTerms() {
        String text = "Terms and Conditions";
        return new Link(text, new ThemeResource("terms"));
    }

    private Component getPrivacy() {
        String text = "Privacy";
        return new Link(text, new ThemeResource("privacy"));
    }

    private Component getCopyright() {
        String text = VaadinIcons.COPYRIGHT.getHtml() + " 2017 Ngutu";
        return new Label(text, ContentMode.HTML);
    }

}
