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
import db.news.NewsArticle;

/**
 *
 * @author zua
 */
public class NgutuFacebookAPI {

    public static final Version VERSION = Version.LATEST;

    private String host;
    private String fragment;

    private FacebookProperties properties;

    private String code;
    private AccessToken accessToken;

    private User user;

    public NgutuFacebookAPI(String fragment) {
        this.fragment = fragment;
        properties = new FacebookProperties();
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
                .addPermission(FacebookPermissions.PUBLISH_ACTIONS)
                .addPermission(FacebookPermissions.MANAGE_PAGES)
                .addPermission(FacebookPermissions.PUBLISH_PAGES);
        FacebookClient client = new DefaultFacebookClient(VERSION);
        String loginDialogUrlString = client.getLoginDialogUrl(properties.getAppId(), getRedirectUrl(), scopeBuilder);
        Page.getCurrent().open(loginDialogUrlString, "_self");
    }

    public String getRedirectUrl() {
        System.out.printf("(host) -> (%s)\n", getHost());
        return getHost() + fragment;
    }

    private String getHost() {
        return Page.getCurrent().getLocation().toASCIIString().split(fragment)[0];
    }

    public User fetchUser(String code) {
        accessToken = new DefaultFacebookClient(VERSION)
                .obtainUserAccessToken(
                        properties.getAppId(),
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

}
