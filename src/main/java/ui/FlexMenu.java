/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import db.news.FlexUser;

/**
 *
 * @author zua
 */
public class FlexMenu extends HorizontalLayout {

    private final FlexUser user;
    private final FlexLogoBar logoBar;
    private final FlexMenuBar menuBar;
    private final LogoutButton logoutButton;
    
    public FlexMenu(FlexUser user) {
        this.user = user;
        this.logoBar = new FlexLogoBar(user);
        this.menuBar = new FlexMenuBar(user);
                
        logoutButton = new LogoutButton();
        logoutButton.addClickListener(event -> {
            getSession().setAttribute("user", null);
            Page.getCurrent().setLocation("/flex-app");
        });
        //logoutButton.addUsername(user.getUsername());

        super.setSizeFull();
        super.setHeight("2cm");
        super.addComponents(menuBar, logoBar, logoutButton);
        super.setComponentAlignment(logoBar, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(logoutButton, Alignment.MIDDLE_RIGHT);
        super.setStyleName("flex-menu");
    }

    public FlexUser getUser() {
        return user;
    }

    public FlexLogoBar getLogoBar() {
        return logoBar;
    }

    public FlexMenuBar getMenuBar() {
        return menuBar;
    }
}
