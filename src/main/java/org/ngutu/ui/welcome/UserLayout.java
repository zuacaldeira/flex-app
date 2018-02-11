/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;
import db.auth.FlexUser;
import org.ngutu.ui.share.FacebookLoginButton;
import org.ngutu.ui.share.FacebookLogoutButton;

/**
 *
 * @author zua
 */
public class UserLayout extends HorizontalLayout {

    private static final long serialVersionUID = -5697064550871685733L;

    private Image picture;
    private FacebookLoginButton facebookLogin;
    private FacebookLogoutButton facebookLogout;

    public UserLayout() {
        super.setSpacing(false);
        initFacebookButton();
        initPicture();
    }

    private void initPicture() {
        FlexUser user = getUser();
        if (user != null && user.getUserInfo() != null && user.getUserInfo().getPicture() != null) {
            picture = new Image(null, new ExternalResource(user.getUserInfo().getPicture()));
            picture.setWidth("32px");
            picture.setHeight("32px");
            picture.setStyleName("circle gravatar");
            addComponent(picture);
            setComponentAlignment(picture, Alignment.MIDDLE_CENTER);
        }
    }

    private void initFacebookButton() {
        if (getUser() == null) {
            facebookLogin = new FacebookLoginButton();
            addComponent(facebookLogin);
            setComponentAlignment(facebookLogin, Alignment.MIDDLE_CENTER);
        }
    }

    private FlexUser getUser() {
        if (UI.getCurrent() != null) {
            return (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        }
        return null;
    }

}
