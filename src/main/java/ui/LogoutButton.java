/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.icons.VaadinIcons;

/**
 *
 * @author zua
 */
public class LogoutButton extends FlexButton {

    public LogoutButton() {
        super("", VaadinIcons.POWER_OFF);
        super.setSizeUndefined();
        super.addStyleName("logout");
    }
    
    public void addUsername(String username) {
        setCaption(username);
    }
    
    
}
