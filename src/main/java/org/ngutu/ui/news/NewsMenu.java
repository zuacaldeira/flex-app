/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import components.LogoutButton;
import data.DataProviderType;
import db.FlexUser;
import ui.FlexViews;
import ui.NewsUI;
import view.logo.FacebookButton;
import view.logo.FlexLogo;
import view.logo.TwitterButton;
import view.menu.CanPopulate;

/**
 *
 * @author zua
 */
public class NewsMenu extends HorizontalLayout implements CanPopulate {

    private static final long serialVersionUID = 8366211712669711650L;

    private final FlexUser user;

    private FlexLogo logo;

    private NewsMenuBar menuBar;
    private TextField searchBox;
    private HorizontalLayout actions;

    private LogoutButton logoutButton;
    private FacebookButton facebookButton;
    private TwitterButton twitterButton;
    private HorizontalLayout userActions;
    

    public NewsMenu(FlexUser user) {
        this.user = user;
        initLogo();
        initMenuBar();
        initSearchBox();
        initLogoutButton();
        initSocialButtons();
        initActions();
        initUserActions();
        super.setSizeFull();
        super.setMargin(false);
        super.addComponent(logo);
        super.addComponent(actions);
        super.addComponent(userActions);
        super.setExpandRatio(logo, .2f);
        super.setExpandRatio(actions, .6f);
        super.setExpandRatio(userActions, .2f);
        super.setComponentAlignment(logo, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(actions, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(userActions, Alignment.MIDDLE_RIGHT);
        super.setStyleName("flex-menu");
    }
    
    private void initSearchBox() {
        searchBox = new TextField();
        searchBox.setCaptionAsHtml(true);
        searchBox.setPlaceholder("Search");
        searchBox.setStyleName("search-box");
        searchBox.addValueChangeListener(e -> {
            Notification.show("Clicked on search " + e.getValue());
            NewsBody body = getBody();
            body.populate(DataProviderType.SEARCH, e.getValue());
        });
    }
    
    private void initLogoutButton() {
        logoutButton = new LogoutButton();
        logoutButton.addClickListener(e -> {
            getUI().getSession().setAttribute("user", null);
            getUI().getNavigator().navigateTo(FlexViews.WELCOME);
        });
    }
    
    private void initSocialButtons() {
        facebookButton = new FacebookButton();
        twitterButton = new TwitterButton();
    }
    
    /*private void initBooksButton() {
        booksButton = new BooksButton();
        booksButton.addClickListener(e -> {
            getUI().getNavigator().navigateTo(FlexViews.BOOKS);
        });
    }*/
    
    private NewsBody getBody() {
        return ((NewsUI) UI.getCurrent()).getMainView().getBody();
    }

    public FlexUser getUser() {
        return user;
    }

    public FlexLogo getLogo() {
        return logo;
    }

    public NewsMenuBar getMenuBar() {
        return menuBar;
    }

    private void initMenuBar() {
        this.menuBar = new NewsMenuBar(user);
    }

    private void initLogo() {
        logo = new FlexLogo();
        logo.addLayoutClickListener(event -> {
            UI.getCurrent().getNavigator().navigateTo(FlexViews.WELCOME);
        });
    }

    @Override
    public void populate() {
        menuBar.populate();
    }

    private void initActions() {
        actions = new HorizontalLayout(menuBar, searchBox);
        actions.setSizeUndefined();
    }

    private void initUserActions() {
        userActions = new HorizontalLayout(facebookButton, twitterButton, logoutButton);
        userActions.setSizeUndefined();
    }

}
