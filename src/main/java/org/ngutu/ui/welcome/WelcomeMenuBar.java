/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.ValoTheme;
import db.FlexUser;
import org.ngutu.ui.auth0.NgutuAuthAPI;
import org.ngutu.ui.viewproviders.FlexViews;

/**
 *
 * @author zua
 */
public final class WelcomeMenuBar extends MenuBar {

    private static final long serialVersionUID = -1299703352057116843L;

    private final FlexUser user;

    // Main Menu (top level)
    private MenuItem top;
    private MenuItem home;
    private MenuItem news;
    private MenuItem books;
    private MenuItem login;
    private MenuItem logout;

    public WelcomeMenuBar(FlexUser user) {
        this.user = user;
        this.initMenuItems();
        setSizeUndefined();
        setAutoOpen(true);
        setStyleName("news-menu-bar");
        addStyleName(ValoTheme.MENUBAR_BORDERLESS);
    }

    protected void initMenuItems() {
        home = addItem("", VaadinIcons.HOME, (item -> {
            getUI().getNavigator().navigateTo(FlexViews.WELCOME);
        }));
        top = addItem("", VaadinIcons.GRID_SMALL, null);
        top.setStyleName("menu-bar-top");
        
        news = top.addItem("News", VaadinIcons.NEWSPAPER, (item -> {
            getUI().getNavigator().navigateTo(FlexViews.NEWS);
        }));
        books = top.addItem("Books", VaadinIcons.BOOK, (item -> {
            getUI().getNavigator().navigateTo(FlexViews.BOOKS);
        }));
        if (user == null) {
            login = addItem("", VaadinIcons.SIGN_IN, (selectedItem) -> {
                NgutuAuthAPI authAPI = new NgutuAuthAPI(getUI().getNavigator().getState());
                authAPI.authorize();
            });
            login.setDescription("LOGIN");
        }
        if (user != null) {
            logout = addItem("", VaadinIcons.SIGN_OUT, (selectedItem) -> {
                getUI().getSession().setAttribute("user", null);
                getUI().getNavigator().navigateTo(FlexViews.WELCOME);
            });
            logout.setDescription("LOGOUT");
        }
    }

    public MenuItem getNews() {
        return news;
    }

    public FlexUser getUser() {
        return user;
    }
}
