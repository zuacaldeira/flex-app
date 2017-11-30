/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import components.FlexPanel;
import db.FlexUser;

/**
 *
 * @author zua
 */
public class WelcomeBody extends FlexPanel {

    private static final long serialVersionUID = 6273025631274336910L;

    private final FlexUser user;
    private AboutUs aboutUs;

    public WelcomeBody(FlexUser user) {
        this.user = user;
        initBody();
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
