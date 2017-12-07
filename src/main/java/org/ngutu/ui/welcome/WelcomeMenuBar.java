/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import db.FlexUser;
import org.ngutu.ui.share.NgutuFacebookAPI;
import org.ngutu.ui.viewproviders.FlexViews;

/**
 *
 * @author zua
 */
public final class WelcomeMenuBar extends MenuBar {

    private static final long serialVersionUID = -1299703352057116843L;

    // Main Menu (top level)
    private MenuItem news;
    private MenuItem books;
    private MenuItem login;
    private MenuItem logout;

    public WelcomeMenuBar() {
        this.initMenuItems();
        setSizeUndefined();
        setAutoOpen(true);
        setStyleName("news-menu-bar");
        addStyleName(ValoTheme.MENUBAR_BORDERLESS);
    }

    protected void initMenuItems() {    
        FlexUser user = getUser();
        news = addItem("News", null, (item -> {
            getUI().getNavigator().navigateTo(FlexViews.NEWS);
        }));
        books = addItem("Books", null, (item -> {
            getUI().getNavigator().navigateTo(FlexViews.BOOKS);
        }));
        if (user == null) {
            login = addItem("Login", VaadinIcons.SIGN_IN, (selectedItem) -> {
                NgutuFacebookAPI authAPI = new NgutuFacebookAPI(
                        Page.getCurrent().getLocation().getHost(), getUI().getNavigator().getState());
                authAPI.authorize();
            });
            login.setDescription("LOGIN");
        }
        if (user != null) {
            logout = addItem("Logout " + user.getUserInfo().getGivenName(), VaadinIcons.SIGN_OUT, (selectedItem) -> {
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
        return (FlexUser) UI.getCurrent().getSession().getAttribute("user");
    }
}
