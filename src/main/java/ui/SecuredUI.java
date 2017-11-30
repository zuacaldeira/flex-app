/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import org.ngutu.ui.viewproviders.BooksViewProvider;
import org.ngutu.ui.viewproviders.NewsViewProvider;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import org.ngutu.ui.viewproviders.WelcomeViewProvider;

/**
 *
 * @author zua
 */
@Theme("mytheme")
public abstract class SecuredUI extends UI {

    private static final long serialVersionUID = 2637212442082775079L;
    private Navigator navigator;

    public SecuredUI() {
        navigator = new Navigator(this, this);
        navigator.addProvider(new WelcomeViewProvider());
        navigator.addProvider(new NewsViewProvider());
        navigator.addProvider(new BooksViewProvider());
    }
    
    
    public String getCurrentUser() {
        if (getSession().getAttribute("user") != null) {
            return (String) getSession().getAttribute("user");
        } else {
            return null;
        }
    }
}
