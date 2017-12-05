/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.share;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.GraphResponse;
import com.restfb.types.User;
import com.vaadin.server.Page;
import com.vaadin.ui.UI;
import db.FlexUser;
import db.NewsArticle;

/**
 *
 * @author zua
 */
public class NgutuFacebookAPI {

    public static final String APP_ID = "311537906014506";
    public static final String APP_SECRET = "31dd2816aa5d8315b8d318a735080bcb";
    public static final Version VERSION = Version.LATEST;   
    private static final String APP_URL = "https://ngutu.herokuapp.com/";

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
        FacebookClient client = new DefaultFacebookClient(VERSION);
        String loginDialogUrlString = client.getLoginDialogUrl(APP_ID, getRedirectUrl(), scopeBuilder);
        Page.getCurrent().open(loginDialogUrlString, "_self");
    }

    public String getRedirectUrl() {
        if(Page.getCurrent().getLocation().getHost().contains("localhost")) {
            return "http://localhost:8080/" + redirectUrlFragment;
        }
        return APP_URL;
    }
    
    public User fetchUserWithAccessToken(String accessToken) {
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, NgutuFacebookAPI.APP_SECRET, NgutuFacebookAPI.VERSION);        
        User me = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "id, name, email, picture, locale, first_name"));
        System.out.printf("(Name, id) = (%s, %s)\n", me.getName(), me.getId());
        return me;
    }
    
    public User fetchUserWithCode(String code) {
        FacebookClient facebookClient = new DefaultFacebookClient(code, NgutuFacebookAPI.APP_SECRET, NgutuFacebookAPI.VERSION);        
        AccessToken accessToken = facebookClient.obtainUserAccessToken(NgutuFacebookAPI.APP_ID, NgutuFacebookAPI.APP_SECRET, getRedirectUrl(), code);
        return fetchUserWithAccessToken(accessToken.getAccessToken());
    }
    

    public void share(NewsArticle article, String message) {
        FlexUser user = (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        String accessToken = (String) UI.getCurrent().getSession().getAttribute("access_token");
        
        FacebookClient facebookClient = new DefaultFacebookClient(accessToken, NgutuFacebookAPI.APP_SECRET, NgutuFacebookAPI.VERSION);        
        GraphResponse response = facebookClient.publish(user.getUserInfo().getSub(), GraphResponse.class);
        System.out.println("ID -> " + response.getId());
        System.out.println("POST ID -> " + response.getPostId());
        System.out.println("TIMELINE ID -> " + response.getTimelineId());
    }
}
