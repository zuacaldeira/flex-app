/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.share;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.User;
import com.vaadin.server.Page;
import db.NewsArticle;

/**
 *
 * @author zua
 */
public class ShareOnFacebook {
    
    public void obtainAccessToken() {
        AccessToken extendedAccessToken = 
                new DefaultFacebookClient(FacebookApp.ACCESS_TOKEN, FacebookApp.VERSION)
                    .obtainExtendedAccessToken(FacebookApp.APP_ID, FacebookApp.APP_SECRET, FacebookApp.ACCESS_TOKEN);        
        System.out.println("OLD::A_TOKEN:: " + FacebookApp.ACCESS_TOKEN);
        System.out.println("NEW::A_TOKEN:: " + extendedAccessToken.getAccessToken());
        System.out.println("NEW::A_TOKEN_EXPIRES:: " + extendedAccessToken.getExpires());
    }

    public void share(NewsArticle article, String message) {

        FacebookClient facebookClient = new DefaultFacebookClient(FacebookApp.ACCESS_TOKEN, FacebookApp.APP_SECRET, FacebookApp.VERSION);        
        User me = facebookClient.fetchObject("me", User.class);
        System.out.printf("(User, email) = (%s, %s)\n", me.getName(), me.getEmail());

        User cocacola = facebookClient.fetchObject("cocacola", User.class);
        System.out.printf("(User, email) = (%s, %s)\n", cocacola.getName(), cocacola.getEmail());

        ScopeBuilder scopeBuilder = new ScopeBuilder();

        FacebookClient client = new DefaultFacebookClient(FacebookApp.ACCESS_TOKEN, FacebookApp.VERSION);
        String loginDialogUrlString = client.getLoginDialogUrl(FacebookApp.APP_ID, "http://ngutu.org", scopeBuilder);
        Page.getCurrent().setLocation(loginDialogUrlString);
    }

}
