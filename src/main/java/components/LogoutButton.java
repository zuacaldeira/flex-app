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
    private String username;

    public LogoutButton() {
        username = getUsername();
        super.setCaption(getLogoutCaption());
        super.setIcon(VaadinIcons.USER);
        super.setSizeUndefined();
        super.addClickListener(event -> {
            if(username == null) {
                login();
            }
            else {
                logout();
            }
        });
    }

    private void login() {
        getUI().getPage().setLocation("https://ngutu.eu.auth0.com/login?client=K8hEG_ew0eF4fv9tRDY1RZ72RjPK-n_Q");
    }
    
    private void logout() {
        UI.getCurrent().getSession().setAttribute("userId", null);
    }
    
    private String getLogoutCaption() {
        return (username != null) ? username : "Login";
    }
    
    private static String getUsername() {
        if (UI.getCurrent() != null && UI.getCurrent().getSession() != null && UI.getCurrent().getSession().getAttribute("user") != null) {
            return ((FlexUser) UI.getCurrent().getSession().getAttribute("userId")).getUsername();
        } else {
            return null;
        }
    }

}
