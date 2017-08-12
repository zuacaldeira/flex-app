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
import com.vaadin.ui.Label;
import db.news.FlexUser;

/**
 *
 * @author zua
 */
public class FlexLogo extends HorizontalLayout {
    private HorizontalLayout context;
    private Label section;
    private Label username;
    // Logout button 
    private final LogoutButton logoutButton;
    private final FlexUser user;
    
    public FlexLogo(FlexUser user) {
        this.user = user;
        section = new Label("Latest");
        section.setStyleName("section");
        
        username = new Label(user.getUsername());
        
        context = new HorizontalLayout(username, new Label("|"), section);
        context.setStyleName("context");
        context.setSizeUndefined();
        context.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        context.setMargin(new MarginInfo(false, false, false, true));
        

        logoutButton = new LogoutButton();
        logoutButton.addClickListener(event -> {
            getSession().setAttribute("user", null);
            Page.getCurrent().setLocation("/flex-app");
        });
        //logoutButton.addUsername(user.getUsername());
        
        super.setHeightUndefined();
        super.setWidth("100%");
        super.setSpacing(true);
        super.setMargin(false);
        super.setStyleName("flex-logo");
        super.addComponents(
            context, 
            logoutButton);
        super.setComponentAlignment(context, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(logoutButton, Alignment.MIDDLE_RIGHT);
    }
    
    public void setNavigationContext(String context) {
        System.out.println("Updating context");
        section.setValue(context);
    }
}
