/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.share;

import com.vaadin.ui.UI;
import db.news.NewsArticle;
import ui.NgutuUI;

/**
 *
 * @author zua
 */
public class ShareOnFacebook {
    
    public void shareAsNgutu(NewsArticle article, String message) {
        ((NgutuUI)UI.getCurrent()).getFacebookAPI().shareAsNgutu(article, message);
    }
    public void share(NewsArticle article, String message) {
        ((NgutuUI)UI.getCurrent()).getFacebookAPI().shareAsUser(article, message);
    }
    public void like(NewsArticle article, String message) {
        ((NgutuUI)UI.getCurrent()).getFacebookAPI().shareAsUser(article, message);
    }
    public void recommend(NewsArticle article, String message) {
        ((NgutuUI)UI.getCurrent()).getFacebookAPI().shareAsUser(article, message);
    }
    
}
