/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.share;

import com.vaadin.ui.UI;
import db.NewsArticle;

/**
 *
 * @author zua
 */
public class ShareOnFacebook {
    
    public void share(NewsArticle article, String message) {
        NgutuFacebookAPI authAPI = new NgutuFacebookAPI(UI.getCurrent().getNavigator().getState());
        authAPI.authorize();
        authAPI.share(article, message);
    }
}
