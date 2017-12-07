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
import db.NewsArticle;

/**
 *
 * @author zua
 */
public class NgutuFacebookAPI {

    public static final String APP_ID = "311537906014506";
    public static final String APP_SECRET = "31dd2816aa5d8315b8d318a735080bcb";
    public static final Version VERSION = Version.LATEST;   
    
    private final String host;
    private final String fragment;
    private DefaultFacebookClient facebookClient;
    private String code;
    private AccessToken accessToken;
    private User me;
    
    public NgutuFacebookAPI(String fragment) {
        this.host = "https://ngutu.herokuapp.com/";
        this.fragment = fragment;
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
        System.out.printf("(host, fragment) -> (%s, %s)\n", host, fragment);
        return host + fragment;
    }
    
    public User fetchUser(String code) {
        accessToken = new DefaultFacebookClient(VERSION)
                .obtainUserAccessToken(APP_ID, APP_SECRET, getRedirectUrl(), code);
        facebookClient = new DefaultFacebookClient(accessToken.getAccessToken(), NgutuFacebookAPI.APP_SECRET, NgutuFacebookAPI.VERSION);        
        
        me = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "id, name, email, picture, locale, first_name"));
        System.out.printf("(Name, id) = (%s, %s)\n", me.getName(), me.getId());
        return me;
    }
    
    public void share(NewsArticle article, String message) {
        GraphResponse response = facebookClient.publish("me", GraphResponse.class, 
                Parameter.with("message", createMessage(article, message)));
        System.out.println("ID -> " + response.getId());
        System.out.println("POST ID -> " + response.getPostId());
        System.out.println("TIMELINE ID -> " + response.getTimelineId());
    }

    private String createMessage(NewsArticle article, String message) {
        return message + ": " + article.getTitle();
    }

}
