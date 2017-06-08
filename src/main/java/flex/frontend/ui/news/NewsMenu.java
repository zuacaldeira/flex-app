/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import flex.frontend.ui.FlexButton;
import flex.frontend.ui.FlexMenu;
import flex.frontend.ui.bantu.HomeButton;
import flex.frontend.ui.news.article.SingleFieldDialog;

/**
 *
 * @author zua
 */
public class NewsMenu extends FlexMenu {

    private FlexButton homeButton;
    private FlexButton searchButton;
    private FlexButton categoriesButton;
    private FlexButton publishersButton;
    private FlexButton authorsButton;
    private FlexWindow window;
    
    public NewsMenu() {
    }

    @Override
    protected void addActions() {
        addHomeButton();
        addCategoriesButton();
        addPublishersButton();
        addAuthorsButton();
        addSearchButton();
    }

    private void addHomeButton() {
        homeButton = new HomeButton();
        addComponent(homeButton);
    }

    private void addSearchButton() {
        searchButton = new FlexButton(VaadinIcons.SEARCH);
        searchButton.setSizeUndefined();
        searchButton.addClickListener(event -> {
            SingleFieldDialog dialog = new SingleFieldDialog("Search");
            Window w = new FlexWindow("Search", dialog);
            UI.getCurrent().addWindow(w);
        });
        addComponent(searchButton);
    }

    private void addCategoriesButton() {
        categoriesButton = new FlexButton("Categories", VaadinIcons.ACCORDION_MENU);
        addComponent(categoriesButton);
        categoriesButton.addClickListener(event -> {
            Page.getCurrent().setLocation("news/categories");
        });
    }

    private void addPublishersButton() {
        publishersButton = new FlexButton("Publishers", VaadinIcons.BUILDING);
        publishersButton.addClickListener(event -> {
            Page.getCurrent().setLocation("news/publishers");
        });
        addComponent(publishersButton);
    }

    private void addAuthorsButton() {
        authorsButton = new FlexButton("Authors", VaadinIcons.USER);
        authorsButton.addClickListener(event -> {
            Page.getCurrent().setLocation("news/authors");
        });
        addComponent(authorsButton);
    }

    public FlexButton getHomeButton() {
        return homeButton;
    }

    public FlexButton getSearchButton() {
        return searchButton;
    }
}
