/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.common;

import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import db.auth.FlexUser;
import org.ngutu.ui.logo.FlexLogo;
import org.ngutu.ui.news.MenuActions;
import org.ngutu.ui.share.FacebookLoginButton;
import org.ngutu.ui.share.FacebookLogoutButton;

/**
 *
 * @author zua
 */
public abstract class AbstractMenu extends HorizontalLayout {

    private static final long serialVersionUID = 1394190815709078040L;

    private FlexLogo logo;
    private TextField searchBox;

    private HorizontalLayout actions;
    private FlexButton facebookButton;
    private Image picture;

    public static final String MENU_HEIGHT = "64px";

    public AbstractMenu() {
        initLogo();
        initActions();
        super.addComponents(logo, actions);
        super.setComponentAlignment(logo, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(actions, Alignment.MIDDLE_RIGHT);
        super.setWidth("100%");
        super.setExpandRatio(actions, 1f);
        super.setHeight(MENU_HEIGHT);
        super.setSpacing(true);
        super.setMargin(new MarginInfo(false, true));
        super.setStyleName("menu");
    }
    
    protected abstract MenuActions createMenuActions();

    public FlexLogo getLogo() {
        return logo;
    }

    private void initLogo() {
        logo = new FlexLogo();
    }

    private FlexUser getUser() {
        if (UI.getCurrent() != null) {
            return (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        }
        return null;
    }

    private void initActions() {
        initFacebookButtons();
        initPicture();
        actions = createMenuActions();
        actions.addComponent(facebookButton);
        actions.addComponent(picture);
    }

    private void initPicture() {
        FlexUser user = getUser();
        if (user != null && user.getUserInfo() != null && user.getUserInfo().getPicture() != null) {
            picture = new Image(null, new ExternalResource(user.getUserInfo().getPicture()));
            picture.setWidth("48px");
            picture.setHeight("48px");
            picture.setStyleName("circle");
        }
        else {
            picture = new Image();
        }
        
        picture.addClickListener(click -> {
            Notification.show("TODO: Navigate to User Profile", Notification.Type.WARNING_MESSAGE);
        });
    }

    public abstract AbstractBody getBody();

    public final TextField getSearchBox() {
        return searchBox;
    }

    public HorizontalLayout getActions() {
        return actions;
    }

    public Image getPicture() {
        return picture;
    }

    private void initFacebookButtons() {
        if (getUser() == null) {
            facebookButton = new FacebookLoginButton();
        } else {
            facebookButton = new FacebookLogoutButton();
        }
    }

    public FlexButton getFacebookButton() {
        return facebookButton;
    }

    public void setFacebookButton(FlexButton facebookButton) {
        this.facebookButton = facebookButton;
    }    
    
}
