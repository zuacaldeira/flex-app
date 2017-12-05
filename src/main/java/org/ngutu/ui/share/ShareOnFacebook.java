/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.share;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.GraphResponse;
import com.restfb.types.User;
import db.NewsArticle;

/**
 *
 * @author zua
 */
public class ShareOnFacebook {
    
    public void share(NewsArticle article, String message) {
        FacebookClient facebookClient = new DefaultFacebookClient(NgutuFacebookAPI.ACCESS_TOKEN, NgutuFacebookAPI.APP_SECRET, NgutuFacebookAPI.VERSION);        
        User me = facebookClient.fetchObject("me", User.class);
        System.out.printf("(User, email) = (%s, %s)\n", me.getName(), me.getEmail());

        User cocacola = facebookClient.fetchObject("cocacola", User.class);
        System.out.printf("(User, email) = (%s, %s)\n", cocacola.getName(), cocacola.getEmail());
        
        GraphResponse publishMessageResponse = facebookClient.publish("me/feed", GraphResponse.class, Parameter.with("message", "Ngutu's RestFB Test"));
        System.out.println("Published message ID: " + publishMessageResponse.getId());
    }
}
