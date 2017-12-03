/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import db.FlexUser;
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
    private WelcomeMenuBar welcomeMenuBar;
    private Image picture;

    public WelcomeMenu(FlexUser user) {
        this.user = user;
        initLogo();
        initActions();
        super.setSizeFull();
        super.setMargin(new MarginInfo(false, true, false, false));
        super.addComponents(logo, actions);
        super.setComponentAlignment(logo, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(actions, Alignment.MIDDLE_RIGHT);
        super.setStyleName("flex-menu");
    }

    private void initActions() {
        actions = new HorizontalLayout();
        actions.setMargin(false);
        actions.setSpacing(false);
        initMenuBar();
        initPicture();
    }

    private void initPicture() {
        if (user != null && user.getUserInfo() != null && user.getUserInfo().getPicture() != null) {
            picture = new Image(null, new ExternalResource(user.getUserInfo().getPicture()));
            picture.setWidth("40px");
            picture.setHeight("40px");
            picture.setStyleName("circle gravatar");
            actions.addComponent(picture);
        }
    }

    private void initMenuBar() {
        welcomeMenuBar = new WelcomeMenuBar(user);
        actions.addComponent(welcomeMenuBar);
        actions.setComponentAlignment(welcomeMenuBar, Alignment.MIDDLE_CENTER);
    }

    public FlexUser getUser() {
        return user;
    }

    public FlexLogo getLogo() {
        return logo;
    }

    private void initLogo() {
        logo = new FlexLogo();
    }
}
