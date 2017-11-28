/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.UI;
import db.FlexUser;
import com.auth0.client.auth.AuthAPI;
import com.vaadin.server.Page;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Window;
import ui.NgutuAuthAPI;

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
            if (username == null) {
                login();
            } else {
                logout();
            }
        });
    }

    private void login() {
        AuthAPI authAPI = new NgutuAuthAPI();
        String url = authAPI.authorizeUrl("https://ngutu.herokuapp.com/news")
                .withConnection("gmail")
                .withAudience("https://ngutu.eu.auth0.com/api/v2/")
                .withScope("openid contacts")
                .withState("state123")
                .build();
        Page.getCurrent().open(url, "_top");
    }

    private void logout() {
        UI.getCurrent().getSession().setAttribute("user", null);
    }

    private String getLogoutCaption() {
        return (username != null) ? username : "Login";
    }

    private static String getUsername() {
        if (UI.getCurrent() != null && UI.getCurrent().getSession() != null && UI.getCurrent().getSession().getAttribute("user") != null) {
            return ((FlexUser) UI.getCurrent().getSession().getAttribute("user")).getUsername();
        } else {
            return null;
        }
    }

}
