/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import components.FlexButton;
import db.FlexUser;
import org.ngutu.ui.viewproviders.FlexViews;
import org.ngutu.ui.logo.FlexLogo;

/**
 *
 * @author zua
 */
public class WelcomeMenu extends HorizontalLayout {

    private static final long serialVersionUID = 8366211712669711650L;

    private final FlexUser user;

    private FlexLogo logo;
    private HorizontalLayout actions;
    private FlexButton aboutUsButton;
    private FlexButton ngutuNewsButton;
    private FlexButton ngutuBooksButton;
    private FlexButton ngutuBlogButton;
    private FlexButton contactUsButton;

    public WelcomeMenu(FlexUser user) {
        this.user = user;
        initMenu();
    }

    private void initMenu() {
        initLogo();
        initActions();
        super.setSizeFull();
        super.setMargin(false);
        super.addComponents(logo, actions);
        super.setComponentAlignment(logo, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(actions, Alignment.MIDDLE_RIGHT);
        super.setStyleName("welcome-menu");
    }

    private void initActions() {
        aboutUsButton = new FlexButton("About Us");
        aboutUsButton.addClickListener(event -> {
            getUI().getNavigator().navigateTo(FlexViews.WELCOME + "/aboutUs");
        });
        
        ngutuNewsButton = new FlexButton("Ngutu News");
        ngutuNewsButton.addClickListener(event -> {
            getUI().getNavigator().navigateTo(FlexViews.NEWS);
        });
        
        ngutuBooksButton = new FlexButton("Ngutu Books");
        ngutuBooksButton.addClickListener(event -> {
            getUI().getNavigator().navigateTo(FlexViews.BOOKS);
        });

        ngutuBlogButton = new FlexButton("Ngutu Blog");
        ngutuBlogButton.addClickListener(event -> {
            getUI().getNavigator().navigateTo(FlexViews.BLOG);
        });

        contactUsButton = new FlexButton("Contact Us");
        contactUsButton.addClickListener(event -> {
            getUI().getNavigator().navigateTo(FlexViews.CONTACTS);
        });

        actions = new HorizontalLayout(
                aboutUsButton, 
                ngutuNewsButton, 
                ngutuBooksButton, 
                ngutuBlogButton,
                contactUsButton);
    }

    public FlexUser getUser() {
        return user;
    }

    public FlexLogo getLogo() {
        return logo;
    }

    private void initLogo() {
        logo = new FlexLogo();
        logo.addLayoutClickListener(event -> {
            UI.getCurrent().getNavigator().navigateTo(FlexViews.WELCOME);
        });
    }
}
