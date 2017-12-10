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

    private FlexLogo logo;
    private HorizontalLayout menuActions;

    private WelcomeMenuBar welcomeMenuBar;
    private Image picture;


    public WelcomeMenu() {
        initLogo();
        initMenuActions();
        super.addComponents(logo, menuActions);
        super.setComponentAlignment(logo, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(menuActions, Alignment.MIDDLE_RIGHT);
        super.setSizeFull();
        super.setSpacing(true);
        super.setMargin(new MarginInfo(false, true, false, false));
        super.setStyleName("flex-menu");
    }

    private void initMenuBar() {
        welcomeMenuBar = new WelcomeMenuBar();
    }
    
    public FlexLogo getLogo() {
        return logo;
    }

    private void initLogo() {
        logo = new FlexLogo();
    }

    private void initMenuActions() {
        initMenuBar();
        initPicture();
        menuActions = new HorizontalLayout(welcomeMenuBar);
        menuActions.setMargin(false);
        menuActions.setSpacing(false);
        menuActions.setSizeUndefined();
        menuActions.setComponentAlignment(welcomeMenuBar, Alignment.MIDDLE_CENTER);
        if(picture != null) {
            menuActions.addComponent(picture);
            menuActions.setComponentAlignment(picture, Alignment.MIDDLE_CENTER);
        }
    }
    
    private void initPicture() {
        FlexUser user = getUser();
        if (user != null && user.getUserInfo() != null && user.getUserInfo().getPicture() != null) {
            picture = new Image(null, new ExternalResource(user.getUserInfo().getPicture()));
            picture.setWidth("32px");
            picture.setHeight("32px");
            picture.setStyleName("circle gravatar");
        }
    }
    
    private FlexUser getUser() {
        if (UI.getCurrent() != null) {
            System.out.println("Found USER -> " + UI.getCurrent().getSession().getAttribute("user"));
            return (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        }
        return null;
    }

}
