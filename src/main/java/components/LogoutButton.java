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
        String audience = "https://ngutu.eu.auth0.com/api/v2/";
        String scope = "openid profile email";
        String responseType = "id_token token";
        String clientId = "K8hEG_ew0eF4fv9tRDY1RZ72RjPK-n_Q";
        String redirectUri = "https://ngutu.herokuapp.com/#!news";
        String state = "MyState";
        getUI().getPage().open(getAuthorizationUrl(audience, scope, responseType, clientId, redirectUri, state), "top");
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

    private String getAuthorizationUrl(String audience, String scope, String responseType, String clientId, String redirectUri, String state) {
        return "https://ngutu.eu.auth0.com/login?" 
                + "scope=" + scope + "&" 
                + "response_type=" + responseType + "&" 
                + "client_id=" + clientId + "&" 
                + "redirect_uri=" + redirectUri;
    }

}
