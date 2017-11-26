/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.UI;
import db.FlexUser;

/**
 *
 * @author zua
 */
public class LogoutButton extends FlexButton {

    private static final long serialVersionUID = 7486170102778425225L;

    public LogoutButton() {
        super.setCaption(getLogoutCaption());
        super.setIcon(VaadinIcons.USER);
        super.setSizeUndefined();
    }

    
    private static String getLogoutCaption() {
        String username = getUsername();
        return (username != null) 
                ? username 
                : "Login";
    }
    
    private static String getUsername() {
        if (UI.getCurrent() != null && UI.getCurrent().getSession() != null && UI.getCurrent().getSession().getAttribute("user") != null) {
            return ((FlexUser) UI.getCurrent().getSession().getAttribute("user")).getUsername();
        } else {
            return null;
        }
    }

}
