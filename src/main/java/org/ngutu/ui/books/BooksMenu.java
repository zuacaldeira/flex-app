/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.books;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import data.DataProviderType;
import org.ngutu.ui.logo.FlexLogo;
import components.CanPopulate;
import db.auth.FlexUser;

/**
 *
 * @author zua
 */
public class BooksMenu extends HorizontalLayout implements CanPopulate {

    private static final long serialVersionUID = 8366211712669711650L;

    private FlexUser user;

    private FlexLogo logo;
    private BooksMenuBar menuBar;
    private TextField searchBox;

    public BooksMenu() {
        initUser();
        initLogo();
        initMenuBar();
        initSearchBox();
        super.setWidth("100%");
        super.setMargin(false);
        super.addComponent(logo);
        super.addComponent(menuBar);
        super.addComponent(searchBox);
        super.setExpandRatio(logo, .2f);
        super.setExpandRatio(menuBar, .6f);
        super.setExpandRatio(searchBox, .2f);
        super.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(menuBar, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(searchBox, Alignment.MIDDLE_CENTER);
        super.setStyleName("books-menu");
    }

    private void initUser() {
        if (UI.getCurrent() != null) {
            System.out.println("Found USER -> " + UI.getCurrent().getSession().getAttribute("user"));
            user = (FlexUser) UI.getCurrent().getSession().getAttribute("user");
            System.out.println("NEWS VIEW USER -> " + user);
        }
    }

    private void initSearchBox() {
        searchBox = new TextField();
        searchBox.setCaptionAsHtml(true);
        searchBox.setPlaceholder("Search");
        searchBox.setStyleName("search-box");
        searchBox.addValueChangeListener(e -> {
            Notification.show("Clicked on search " + e.getValue());
            BooksBody body = getBody();
            body.populate(DataProviderType.SEARCH, e.getValue());
        });
    }

    private BooksBody getBody() {
        return ((BooksView) UI.getCurrent().getContent()).getBody();

    }

    public FlexUser getUser() {
        return user;
    }

    public FlexLogo getLogo() {
        return logo;
    }

    public BooksMenuBar getMenuBar() {
        return menuBar;
    }

    private void initMenuBar() {
        this.menuBar = new BooksMenuBar(user);
    }

    private void initLogo() {
        logo = new FlexLogo();
    }

    @Override
    public void populate() {
        menuBar.populate();
    }

}
