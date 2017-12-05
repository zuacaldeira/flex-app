/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import com.vaadin.ui.UI;
import components.FlexPanel;
import db.FlexUser;

/**
 *
 * @author zua
 */
public class WelcomeBody extends FlexPanel {

    private static final long serialVersionUID = 6273025631274336910L;

    private FlexUser user;
    private AboutUs aboutUs;

    public WelcomeBody() {
        initUser();
        initBody();
    }

    private void initUser() {
        if (UI.getCurrent() != null) {
            System.out.println("Found USER -> " + UI.getCurrent().getSession().getAttribute("user"));
            user = (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        }
    }

    public FlexUser getUser() {
        return user;
    }

    private void initBody() {
        initAboutUs();
        setContent(aboutUs);
        super.addStyleName("welcome-body");
        super.setSizeUndefined();
    }

    private void initAboutUs() {
        aboutUs = new AboutUs();
    }
}
