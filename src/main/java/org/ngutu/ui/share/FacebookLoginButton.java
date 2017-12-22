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
import ui.NgutuUI;

/**
 *
 * @author zua
 */
public class FacebookLoginButton extends FlexButton {

    private static final long serialVersionUID = -3207358902945940207L;

    public FacebookLoginButton() {
        super("Login", VaadinIcons.FACEBOOK_SQUARE);
        super.setStyleName(ValoTheme.BUTTON_PRIMARY + " flex-button "  + ValoTheme.BUTTON_QUIET);
        super.addClickListener(click -> {
            if (UI.getCurrent() != null && ((NgutuUI) UI.getCurrent()).getFacebookAPI() != null) {
                NgutuFacebookAPI authAPI = ((NgutuUI) UI.getCurrent()).getFacebookAPI();
                authAPI.setNavigationState(UI.getCurrent().getNavigator().getState());
                UI.getCurrent().getSession().setAttribute("navigationState", UI.getCurrent().getNavigator().getState());
                authAPI.authorize();
            }
        });
    }

}
