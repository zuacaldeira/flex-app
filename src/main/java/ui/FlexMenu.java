/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import db.news.FlexUser;

/**
 *
 * @author zua
 */
public class FlexMenu extends HorizontalLayout {

    private final FlexUser user;
    private final FlexMenuBar menuBar;
    private final LogoutButton logoutButton;
    
    public FlexMenu(FlexUser user) {
        this.user = user;
        this.menuBar = new FlexMenuBar(user);
                
        logoutButton = new LogoutButton();
        logoutButton.addUsername(user.getUsername());
        logoutButton.addClickListener(event -> {
            getSession().setAttribute("user", null);
            Page.getCurrent().setLocation("/flex-app");
        });
        //logoutButton.addUsername(user.getUsername());

        super.setSizeFull();
        super.setHeight("2cm");
        super.setMargin(new MarginInfo(true, false));
        
        super.addComponents(menuBar, logoutButton);
        super.setComponentAlignment(menuBar, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(logoutButton, Alignment.MIDDLE_RIGHT);
        super.setStyleName("flex-menu");
    }

    public FlexUser getUser() {
        return user;
    }

    public FlexMenuBar getMenuBar() {
        return menuBar;
    }
}
