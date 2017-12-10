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
    public static final String ADMIN_ACCESS_TOKEN = "EAAEbV5I42SoBAPt3MbrZCSmiX4jyFnhiJjeCKsZAU2qpjOwjYHGNZCEc3iJ6PbBUhZBeXzLdqhRprwnnM2lGhsXIxtn6bZBtZCP99SZBEqqqVq7Gj0AGmKR5Gsjft4d6H0TKu6nPx6fE54ORepQPFVgX4hUYtvJEAZAeoE1z1N8FXAZDZD";
    public static final Version VERSION = Version.LATEST;   
    
    private String fragment;
    private DefaultFacebookClient facebookClient;
    private String code;
    private AccessToken accessToken;
    private User me;
    
    public NgutuFacebookAPI(String fragment) {
        this.fragment = fragment;
    }

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
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
        System.out.printf("(host, fragment) -> (%s, %s)\n", getHost(), fragment);
        return getHost() + fragment;
    }
    
    private String getHost() {
        if(Page.getCurrent().getLocation().getHost().contains("localhost")) {
            return "http://localhost:8080/";
        }
        else {
            return "https://ngutu.herokuapp.com/";
        }
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
        GraphResponse response = facebookClient.publish("me/feed", GraphResponse.class, 
                Parameter.with("message", createMessage(article, message)),
                Parameter.with("picture", article.getImageUrl()),
                Parameter.with("link", article.getUrl()));
        System.out.println("ID -> " + response.getId());
        System.out.println("POST ID -> " + response.getPostId());
        System.out.println("TIMELINE ID -> " + response.getTimelineId());
    }

    public void shareAsNgutu(NewsArticle article, String message) {
        DefaultFacebookClient facebookAdminClient = new DefaultFacebookClient(ADMIN_ACCESS_TOKEN, APP_SECRET, VERSION);
        GraphResponse response = facebookAdminClient.publish("me/feed", GraphResponse.class, 
                Parameter.with("message", createMessage(article, message)),
                Parameter.with("link", article.getUrl()));
        System.out.println("ID -> " + response.getId());
        System.out.println("POST ID -> " + response.getPostId());
        System.out.println("TIMELINE ID -> " + response.getTimelineId());
    }


    private String createMessage(NewsArticle article, String message) {
        return message;
    }

}
