/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import db.auth.FlexUser;
import org.ngutu.ui.share.NgutuFacebookAPI;
import org.ngutu.ui.viewproviders.FlexViews;
import ui.NgutuUI;

/**
 *
 * @author zua
 */
public final class WelcomeMenuBar extends MenuBar {

    private static final long serialVersionUID = -1299703352057116843L;

    // Main Menu (top level)
    private MenuItem news;
    private MenuItem books;
    private MenuItem logInOut;
    

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
        logInOut = addItem("", VaadinIcons.POWER_OFF, (item -> {
            if (getUser() != null) {
                getUI().getSession().setAttribute("user", null);
            }
            else if (getUser() == null) {
                if (UI.getCurrent() != null && ((NgutuUI) UI.getCurrent()).getFacebookAPI() != null) {
                    NgutuFacebookAPI authAPI = ((NgutuUI) UI.getCurrent()).getFacebookAPI();
                    authAPI.setFragment(UI.getCurrent().getNavigator().getState());
                    authAPI.authorize();
                }
            }
            getUI().getNavigator().navigateTo(FlexViews.WELCOME);            
        }));
    }

    public MenuItem getNews() {
        return news;
    }

    public FlexUser getUser() {
        return (FlexUser) UI.getCurrent().getSession().getAttribute("user");
    }
}
