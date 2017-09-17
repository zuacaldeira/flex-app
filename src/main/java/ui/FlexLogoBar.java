/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import db.FlexUser;

/**
 *
 * @author zua
 */
public class FlexLogoBar extends HorizontalLayout {
    private HorizontalLayout context;
    private Label section;
    private Label username;
    // Logout button 
    private final FlexUser user;
    
    public FlexLogoBar(FlexUser user) {
        this.user = user;
        section = new Label("Latest");
        section.setStyleName("section");
        
        username = new Label(user.getUsername());

        context = new HorizontalLayout(section, new Label("|"), username);
        context.setStyleName("context");
        context.setComponentAlignment(section, Alignment.MIDDLE_RIGHT);
        context.setComponentAlignment(username, Alignment.MIDDLE_LEFT);
        context.setMargin(false);
        

        
        super.setSizeFull();
        super.setSpacing(true);
        super.setMargin(false);
        super.setStyleName("flex-logo");
        super.addComponent(context);
        super.setComponentAlignment(context, Alignment.MIDDLE_CENTER);
    }
    
    public void setNavigationContext(String context) {
        System.out.println("Updating context");
        section.setValue(context.toLowerCase());
    }
}
