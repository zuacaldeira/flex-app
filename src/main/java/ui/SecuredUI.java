/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import db.FlexUser;
import db.Neo4jSessionFactory;

/**
 *
 * @author zua
 */
@Theme("mytheme")
public abstract class SecuredUI extends UI {

    private static final long serialVersionUID = 2637212442082775079L;
    private Navigator navigator;
    private Neo4jSessionFactory factory = Neo4jSessionFactory.getInstance();
    
    @Override
    public void init(VaadinRequest request) {
        getPage().setTitle("Ngutu. Your portal to the world.");
        navigator = new Navigator(this, this);
        navigator.addProvider(new WelcomeViewProvider());
        navigator.addProvider(new NewsViewProvider());
        //navigator.addProvider(new BooksViewProvider());
        //navigator.navigateTo(FlexViews.WELCOME);
    }

    public FlexUser getCurrentUser() {
        if (getSession().getAttribute("user") != null) {
            return (FlexUser) getSession().getAttribute("user");
        } else {
            return null;
        }
    }
}
