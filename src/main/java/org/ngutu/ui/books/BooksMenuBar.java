/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.books;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import data.BooksProviderType;

/**
 *
 * @author zua
 */
public final class BooksMenuBar extends MenuBar {

    private static final long serialVersionUID = -1299703352057116843L;


    // Main Menu (top level)
    private MenuItem news;
    private MenuItem bestSellers;
    private MenuItem recomended;
    private MenuItem nobelPrizes;
    private MenuItem languages;
    private MenuItem countries;
    private MenuItem logout;

    public BooksMenuBar() {
        initMenuItems();
        setSizeUndefined();
        setAutoOpen(true);
        setStyleName("books-menu-bar");
        addStyleName(ValoTheme.MENUBAR_BORDERLESS);
    }

    protected void initMenuItems() {
        news = addItem("New", null, null);
        recomended = addItem("Recomended", null, (selectedItem) -> {
            updateBody(BooksProviderType.RECOMMENDED);
        });
        bestSellers = addItem("Best Sellers", null,  (selectedItem) -> {
            updateBody(BooksProviderType.BEST_SELLERS);
        });
        nobelPrizes = addItem("Nobel Prizes", null,  (selectedItem) -> {
            updateBody(BooksProviderType.NOBEL_PRIZES);
        });
        languages = addItem("Languages", null,  (selectedItem) -> {
            updateBody(BooksProviderType.LANGUAGES);
        });
        countries = addItem("Countries", null,  (selectedItem) -> {
            updateBody(BooksProviderType.COUNTRIES);
        });
        logout = addItem("", VaadinIcons.SIGN_OUT, (selectedItem) -> {
            if (UI.getCurrent() != null) {
                Notification.show("LOGOUT");
                getSession().setAttribute("user", null);
                UI.getCurrent().getNavigator().navigateTo("");
            }
        });
    }

    public MenuItem getNews() {
        return news;
    }

    public MenuItem getBestSellers() {
        return bestSellers;
    }

    public MenuItem getRecomended() {
        return recomended;
    }

    public MenuItem getLanguages() {
        return languages;
    }

    public MenuItem getCountries() {
        return countries;
    }

    public MenuItem getLogout() {
        return logout;
    }

    private void updateBody(BooksProviderType type) {
        //
    }
}
