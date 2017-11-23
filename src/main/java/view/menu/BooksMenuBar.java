/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.menu;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import db.FlexUser;
import data.DataProviderType;
import services.FlexBooksServiceInterface;
import ui.NewsUI;
import utils.ServiceLocator;
import view.body.BooksBody;
import view.main.FlexBooksView;

/**
 *
 * @author zua
 */
public final class BooksMenuBar extends MenuBar implements CanPopulate {

    private static final long serialVersionUID = -1299703352057116843L;

    private final FlexUser user;
    private final FlexBooksServiceInterface booksService;

    // Main Menu (top level)
    private MenuItem news;
    private MenuItem bestSellers;
    private MenuItem recomended;
    private MenuItem nobelPrizes;
    private MenuItem languages;
    private MenuItem countries;
    private MenuItem logout;

    public BooksMenuBar(FlexUser user) {
        this.user = user;
        booksService = ServiceLocator.getInstance().findBooksService();
    }

    protected void initMenuItems() {
        news = addItem("New", null, null);
        recomended = addItem("Recomended", null, null);
        bestSellers = addItem("Best Sellers", null, null);
        nobelPrizes = addItem("Nobel Prizes", null, null);
        languages = addItem("Languages", null, null);
        countries = addItem("Countries", null, null);
        logout = addItem("", VaadinIcons.SIGN_OUT, (selectedItem) -> {
            if (UI.getCurrent() != null) {
                Notification.show("LOGOUT");
                getSession().setAttribute("user", null);
                UI.getCurrent().getNavigator().navigateTo("");
            }
        });

        setSizeUndefined();
        setAutoOpen(true);
        setStyleName("books-menu-bar");
        addStyleName(ValoTheme.MENUBAR_BORDERLESS);
    }

    @Override
    public void populate() {
        this.initMenuItems();
    }

    @Override
    public NewsUI getUI() {
        return (NewsUI) super.getUI();
    }

    private void updateBody(DataProviderType dataType, String value) {
        BooksBody body = ((FlexBooksView) ((NewsUI)getUI()).getContent()).getBody();
        if (body != null) {
            body.populate(dataType, value);
        } else {
            Notification.show("Body Not Found");
        }
    }

    private String getCategoryCaption(String cat) {
        char c = cat.charAt(0);
        return cat.replaceFirst(
                String.valueOf(c),
                String.valueOf(Character.toUpperCase(c)))
                .replace("-", " ");
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

    public FlexUser getUser() {
        return user;
    }
}
