/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import db.FlexUser;
import ui.view.main.FlexBooksView;
import ui.view.main.FlexNewsView;

/**
 *
 * @author zua
 */
@Theme("mytheme")
public abstract class SecuredUI extends UI {

    private static final long serialVersionUID = 2637212442082775079L;
    Navigator navigator;

    @Override
    public void init(VaadinRequest request) {
        getPage().setTitle("Ngutu. Your portal to the world.");
        if (navigator == null){
            navigator = new Navigator(this, this);
            navigator.addView("", new LoginView());
            navigator.addView(FlexViews.NEWS.name(), new FlexNewsView());
            navigator.addView(FlexViews.BOOKS.name(), new FlexBooksView());
        }
    }

    public FlexUser getCurrentUser() {
        if (getSession().getAttribute("user") != null) {
            return (FlexUser) getSession().getAttribute("user");
        } else {
            return null;
        }
    }
}
