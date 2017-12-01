/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import data.DataProviderType;
import db.FlexUser;
import org.ngutu.ui.viewproviders.FlexViews;
import ui.NgutuUI;
import view.logo.FlexLogo;
import components.FlexButton;
import components.HomeButton;
import org.ngutu.ui.auth0.NgutuAuthAPI;

/**
 *
 * @author zua
 */
public class NewsMenu extends HorizontalLayout {

    private static final long serialVersionUID = 8366211712669711650L;

    private FlexUser user;

    private FlexLogo logo;
    private HorizontalLayout actions;
    private FlexButton home;
    private FlexButton news;
    private FlexButton publishers;
    private FlexButton categories;
    private FlexButton languages;
    private FlexButton countries;
    private FlexButton search;
    private TextField searchBox;
    private Image picture;
    private FlexButton login;
    private FlexButton logout;

    public NewsMenu(FlexUser user) {
        this.user = user;
        initLogo();        
        initActions();
        super.setSizeFull();
        super.setMargin(new MarginInfo(false, true, false, false));
        super.addComponents(logo, actions);
        super.setExpandRatio(actions, 1f);
        super.setComponentAlignment(logo, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(actions, Alignment.MIDDLE_RIGHT);
        super.setStyleName("flex-menu");
        initAccount();
    }

    private void initActions() {
        home = new HomeButton();
        news = new FlexButton("Overview");
        categories = new FlexButton("Categories");
        publishers = new FlexButton("Publishers");
        languages = new FlexButton("Languages");
        countries = new FlexButton("Countries");
        actions = new HorizontalLayout(home, news, categories, publishers, languages, countries);
        actions.setMargin(new MarginInfo(false, true));
        actions.setSpacing(false);
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


    private NewsBody getBody() {
        return ((NgutuUI) UI.getCurrent()).getMainView().getBody();
    }

    public FlexUser getUser() {
        return user;
    }

    public FlexLogo getLogo() {
        return logo;
    }

    private void initLogo() {
        logo = new FlexLogo();
        logo.addLayoutClickListener(event -> {
            UI.getCurrent().getNavigator().navigateTo(FlexViews.WELCOME);
        });
    }

    private void initAccount() {
        if(user != null && user.getUserInfo() != null) {
            initPicture();
            initLogoutButton();
            actions.addComponent(logout);
            super.addComponent(picture);
            setComponentAlignment(picture, Alignment.MIDDLE_CENTER);
        }
        else {
            initLoginButton();
            actions.addComponent(login);
        }
    }

    private void initPicture() {
        picture = new Image(null, new ExternalResource(user.getUserInfo().getPicture()));
        picture.setWidth("32px");
        picture.setHeight("32px");
        picture.setStyleName("circle");
    }
    
    private void initLoginButton() {
        login = new FlexButton("Login");
        login.addClickListener(event -> {
            NgutuAuthAPI authAPI = new NgutuAuthAPI(getUI().getNavigator().getState());
            authAPI.authorize();
        });
    }
    
    private void initLogoutButton() {
        logout = new FlexButton("Logout");
        logout.addClickListener(e -> {
            getUI().getSession().setAttribute("user", null);
            getUI().getNavigator().navigateTo(FlexViews.WELCOME);
        });
    }
    
}
