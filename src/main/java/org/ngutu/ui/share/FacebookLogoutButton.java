/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.share;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import components.FlexButton;
import db.auth.FlexUser;

/**
 *
 * @author zua
 */
public class FacebookLogoutButton extends FlexButton {

    private static final long serialVersionUID = -3207358902945940207L;

    public FacebookLogoutButton() {
        super("Logout " + getUser().getUserInfo().getGivenName(), VaadinIcons.FACEBOOK_SQUARE);
        super.setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        super.addStyleName("facebook-logout-button");
    }
    
     private static FlexUser getUser() {
        if (UI.getCurrent() != null) {
            return (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        }
        return null;
    }
    
}
