/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.share;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.User;
import com.vaadin.server.Page;

/**
 *
 * @author zua
 */
public class NgutuFacebookAPI {

    public static final String APP_ID = "311537906014506";
    public static final String APP_SECRET = "31dd2816aa5d8315b8d318a735080bcb";
    public static final String ACCESS_TOKEN = "EAAEbV5I42SoBADQ520Tm4QlCLFMrqlibibLXZCTonytNAMCDLnKp4wZBu85fu8ZAnhjQGyiwtaC80bLxZAPFVjqXFzxBjIVeclmy9V33e87SpYfwiIZA0ny3YZBqQQTx32JjDLnfHWzaFZBeHxRJwzqEm8eYqUL3TcZD";
    public static final Version VERSION = Version.LATEST;   
    private static final String APP_URL = "http://ngutu.org/";

    private String redirectUrlFragment;
    
    public NgutuFacebookAPI(String redirectUrlFragment) {
        this.redirectUrlFragment = redirectUrlFragment;
    }

    public void authorize() {
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder
                .addPermission(FacebookPermissions.USER_ABOUT_ME)
                .addPermission(FacebookPermissions.EMAIL)
                .addPermission(FacebookPermissions.PUBLIC_PROFILE)
                .addPermission(FacebookPermissions.PUBLISH_ACTIONS);
        FacebookClient client = new DefaultFacebookClient(ACCESS_TOKEN, VERSION);
        String loginDialogUrlString = client.getLoginDialogUrl(APP_ID, getRedirectUrl(), scopeBuilder);
        Page.getCurrent().open(loginDialogUrlString, "_self");
    }

    public String getRedirectUrl() {
        if(Page.getCurrent().getLocation().getHost().contains("localhost")) {
            return "http://localhost:8080/" + redirectUrlFragment;
        }
        return APP_URL + redirectUrlFragment;
    }
    
    public void obtainAccessToken() {
        FacebookClient.AccessToken extendedAccessToken = 
                new DefaultFacebookClient(ACCESS_TOKEN, VERSION)
                    .obtainExtendedAccessToken(APP_ID, APP_SECRET, ACCESS_TOKEN);        
        
        System.out.println("OLD::A_TOKEN:: " + ACCESS_TOKEN);
        System.out.println("NEW::A_TOKEN:: " + extendedAccessToken.getAccessToken());
        System.out.println("NEW::A_TOKEN_EXPIRES:: " + extendedAccessToken.getExpires());
    }

    public User fetchUser(String accessToken) {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, NgutuFacebookAPI.APP_SECRET, NgutuFacebookAPI.VERSION);        
        User me = facebookClient.fetchObject("me", User.class);
        System.out.printf("(User, email) = (%s, %s)\n", me.getName(), me.getEmail());
        return me;
    }

}
