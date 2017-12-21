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
import db.news.NewsArticle;

/**
 *
 * @author zua
 */
public class NgutuFacebookAPI {

    public static final Version VERSION = Version.LATEST;
    private FacebookProperties properties;


    private final String host;
    private String navigationState;

    private AccessToken accessToken;

    private User user;

    public NgutuFacebookAPI(String host, String navigationState) {
        properties = new FacebookProperties();
        this.host = host;
        this.navigationState = navigationState;
    }

    public String getHost() {
        return host;
    }
    
    public String getNavigationState() {
        return navigationState;
    }

    public void setNavigationState(String navigationState) {
        this.navigationState = navigationState;
    }
    


    public void authorize() {
        ScopeBuilder scopeBuilder = new ScopeBuilder();
        scopeBuilder
                .addPermission(FacebookPermissions.USER_ABOUT_ME)
                .addPermission(FacebookPermissions.EMAIL)
                .addPermission(FacebookPermissions.PUBLIC_PROFILE)
                .addPermission(FacebookPermissions.PUBLISH_ACTIONS)
                .addPermission(FacebookPermissions.MANAGE_PAGES)
                .addPermission(FacebookPermissions.PUBLISH_PAGES);
        FacebookClient client = new DefaultFacebookClient(VERSION);
        String loginDialogUrlString = client.getLoginDialogUrl(properties.getAppId(), getLoginCallback(), scopeBuilder);
        Page.getCurrent().open(loginDialogUrlString, "_self");
    }

    public String getLoginCallback() {
        System.out.printf("LOGIN CALLBACK URL host/fragment -> (%s%s)\n", host, extractFragment(navigationState));
        return host + extractFragment(navigationState);
    }

    public String getRedirectUrl() {
        System.out.printf("REDIRECT URL host/fragment -> (%s%s)\n", host, navigationState);
        return host + navigationState;
    }

    public User fetchUser(String code) {
        accessToken = new DefaultFacebookClient(VERSION)
                .obtainUserAccessToken(properties.getAppId(),
                        properties.getAppSecret(),
                        getRedirectUrl(),
                        code);

        DefaultFacebookClient facebookClient = new DefaultFacebookClient(
                accessToken.getAccessToken(),
                properties.getAppSecret(),
                NgutuFacebookAPI.VERSION);
        
        user = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "id, name, email, picture, locale, first_name"));
        
        System.out.printf("(Name, id) = (%s, %s)\n", user.getName(), user.getId());
        return user;
    }

    public void shareAsUser(NewsArticle article, String message) {
        DefaultFacebookClient facebookClient = new DefaultFacebookClient(
                accessToken.getAccessToken(),
                properties.getAppSecret(),
                VERSION);
        GraphResponse response = null;
        if (article.getImageUrl() != null) {
            response = facebookClient.publish("me/feed", GraphResponse.class,
                    Parameter.with("message", createMessage(article, message)),
                    Parameter.with("picture", article.getImageUrl()),
                    Parameter.with("link", article.getUrl()));
        } else {
            response = facebookClient.publish("me/feed", GraphResponse.class,
                    Parameter.with("message", createMessage(article, message)),
                    Parameter.with("link", article.getUrl()));
        }
        System.out.println("ID -> " + response.getId());
        System.out.println("POST ID -> " + response.getPostId());
        System.out.println("TIMELINE ID -> " + response.getTimelineId());
    }

    public void shareAsNgutu(NewsArticle article, String message) {
        DefaultFacebookClient facebookClient = new DefaultFacebookClient(
                accessToken.getAccessToken(),
                properties.getAppSecret(),
                VERSION);

        GraphResponse response = null;
        if (article.getImageUrl() != null) {
            response = facebookClient.publish("177426936176057/feed", GraphResponse.class,
                    Parameter.with("message", createMessage(article, message)),
                    Parameter.with("picture", article.getImageUrl()),
                    Parameter.with("link", article.getUrl()));
        } else {
            response = facebookClient.publish("177426936176057/feed", GraphResponse.class,
                    Parameter.with("message", createMessage(article, message)),
                    Parameter.with("link", article.getUrl()));
        }
        System.out.println("ID -> " + response.getId());
        System.out.println("POST ID -> " + response.getPostId());
        System.out.println("TIMELINE ID -> " + response.getTimelineId());
    }

    private String createMessage(NewsArticle article, String message) {
        return message;
    }

    private String extractFragment(String state) {
        if(state.startsWith("news")) {
            return "news";
        }
        if(state.startsWith("books")) {
            return "books";
        }
        return "";
    }

    public void deauthorize() {
    }
    

}
