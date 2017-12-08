/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.share;

import com.auth0.client.auth.AuthAPI;
import com.vaadin.server.Page;

/**
 *
 * @author zua
 */
public class NgutuAuthAPI extends AuthAPI {

    private static final String DOMAIN = "ngutu.eu.auth0.com";
    private static final String CLIENT_ID = "K8hEG_ew0eF4fv9tRDY1RZ72RjPK-n_Q";
    private static final String CLIENT_SECRET = "oAka59gWaZ0rgnmq61geaMEpcB-RPAANal9M6seQSqeidnHWQK5JIDXeApJ0OJZ5";
    private static final String APP_URL = "https://ngutu.herokuapp.com/";
    private String redirectUrlFragment;
    
    public NgutuAuthAPI(String redirectUrlFragment) {
        super(DOMAIN, CLIENT_ID, CLIENT_SECRET);
        this.redirectUrlFragment = redirectUrlFragment;
    }

    public void authorize() {
        String url = authorizeUrl(getRedirectUrl())
                .withConnection("facebook")
                .withAudience("https://ngutu.eu.auth0.com/api/v2/")
                .withScope("openid email profile contacts")
                .withState("state123")
                .build();
        Page.getCurrent().open(url, "_self");
    }

    public String getRedirectUrl() {
        if(Page.getCurrent().getLocation().getHost().contains("localhost")) {
            return "http://localhost:8080/" + redirectUrlFragment;
        }
        return APP_URL + redirectUrlFragment;
    }
}
