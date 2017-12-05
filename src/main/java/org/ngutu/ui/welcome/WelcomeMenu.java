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
import com.vaadin.ui.UI;
import db.FlexUser;
import org.ngutu.ui.logo.FlexLogo;

/**
 *
 * @author zua
 */
public class WelcomeMenu extends HorizontalLayout {

    private static final long serialVersionUID = 8366211712669711650L;

    private FlexUser user;

    private FlexLogo logo;
    private HorizontalLayout actions;
    private WelcomeMenuBar welcomeMenuBar;
    private Image picture;

    public WelcomeMenu() {
        initUser();
        initLogo();
        initActions();
        super.setSizeFull();
        super.setMargin(new MarginInfo(false, true, false, false));
        super.addComponents(logo, actions);
        super.setComponentAlignment(logo, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(actions, Alignment.MIDDLE_RIGHT);
        super.setStyleName("flex-menu");
    }

    private void initUser() {
        if (UI.getCurrent() != null) {
            System.out.println("Found USER -> " + UI.getCurrent().getSession().getAttribute("user"));
            user = (FlexUser) UI.getCurrent().getSession().getAttribute("user");
            System.out.println("Welcome Menu Session USER -> " + user);
        }
    }

    private void initActions() {
        actions = new HorizontalLayout();
        actions.setMargin(false);
        actions.setSpacing(false);
        initMenuBar();
        initPicture();
    }

    private void initPicture() {
        System.out.println("INIT PICTURE");
        if (user != null) {
            System.out.println("3 - User -> " + user);
            if (user.getUserInfo() != null) {
                System.out.println("2 - UserInfo -> " + user.getUserInfo());
                if (user.getUserInfo().getPicture() != null) {
                    System.out.println("1 - UserInfo Picture -> " + user.getUserInfo().getPicture());
                    picture = new Image(null, new ExternalResource(user.getUserInfo().getPicture()));
                    picture.setWidth("40px");
                    picture.setHeight("40px");
                    picture.setStyleName("circle gravatar");
                    actions.addComponent(picture);
                }
            }
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
