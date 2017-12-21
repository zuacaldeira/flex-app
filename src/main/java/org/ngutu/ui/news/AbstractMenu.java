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
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import db.auth.FlexUser;
import org.ngutu.ui.logo.FlexLogo;
import org.ngutu.ui.share.FacebookLoginButton;
import org.ngutu.ui.share.FacebookLogoutButton;
import org.ngutu.ui.share.NgutuFacebookAPI;
import ui.NgutuUI;

/**
 *
 * @author zua
 */
public abstract class AbstractMenu extends HorizontalLayout {

    private static final long serialVersionUID = 1394190815709078040L;

    private FlexLogo logo;
    private TextField searchBox;

    private HorizontalLayout menuActions;
    private MenuBar menuBar;

    private FacebookLoginButton facebookLoginButton;
    private FacebookLogoutButton facebookLogoutButton;

    private Image picture;

    public AbstractMenu() {
        initLogo();
        initSearchBox();
        initMenuActions();
        super.addComponents(logo, searchBox, menuActions);
        super.setComponentAlignment(logo, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(searchBox, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(menuActions, Alignment.MIDDLE_RIGHT);
        super.setSizeFull();
        super.setHeight("40px");
        super.setSpacing(true);
        super.setMargin(new MarginInfo(false, true, false, false));
        super.setStyleName("flex-menu");
    }

    public FlexLogo getLogo() {
        return logo;
    }

    private void initLogo() {
        logo = new FlexLogo();
    }

    private FlexUser getUser() {
        if (UI.getCurrent() != null) {
            System.out.println("Found USER -> " + UI.getCurrent().getSession().getAttribute("user"));
            return (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        }
        return null;
    }

    private void initSearchBox() {
        searchBox = new TextField();
        searchBox.setCaptionAsHtml(true);
        searchBox.setPlaceholder("Search");
        searchBox.setStyleName("search-box");
        searchBox.setWidth("100%");
        searchBox.addValueChangeListener(e -> {
            search(e.getValue());
        });
    }

    protected abstract void search(String value);

    private void initMenuActions() {
        initMenuBar();
        menuActions = new HorizontalLayout(menuBar);
        menuActions.setMargin(false);
        menuActions.setSpacing(false);
        menuActions.setSizeUndefined();
        menuActions.setComponentAlignment(menuBar, Alignment.MIDDLE_CENTER);
        if (picture != null) {
            menuActions.addComponent(picture);
            menuActions.setComponentAlignment(picture, Alignment.MIDDLE_CENTER);
        }
        initFacebookButtons();
        initPicture();
    }

    private void initPicture() {
        FlexUser user = getUser();
        if (user != null && user.getUserInfo() != null && user.getUserInfo().getPicture() != null) {
            picture = new Image(null, new ExternalResource(user.getUserInfo().getPicture()));
            picture.setWidth("32px");
            picture.setHeight("32px");
            picture.setStyleName("circle gravatar");
        }
    }

    private void initMenuBar() {
        menuBar = createMenuBar();
    }

    protected abstract MenuBar createMenuBar();

    public final TextField getSearchBox() {
        return searchBox;
    }

    public HorizontalLayout getMenuActions() {
        return menuActions;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public Image getPicture() {
        return picture;
    }

    private void initFacebookButtons() {
        if (getUser() == null) {
            facebookLoginButton = new FacebookLoginButton();
            facebookLoginButton.addClickListener(click -> {
                if (UI.getCurrent() != null && ((NgutuUI) UI.getCurrent()).getFacebookAPI() != null) {
                    NgutuFacebookAPI authAPI = ((NgutuUI) UI.getCurrent()).getFacebookAPI();
                    authAPI.setNavigationState(UI.getCurrent().getNavigator().getState());
                    UI.getCurrent().getSession().setAttribute("navigationState", UI.getCurrent().getNavigator().getState());
                    authAPI.authorize();
                }
            });
            menuActions.addComponent(facebookLoginButton);
        } else {
            facebookLogoutButton = new FacebookLogoutButton();
            facebookLogoutButton.addClickListener(click -> {
                if (UI.getCurrent() != null) {
                    UI.getCurrent().getSession().setAttribute("user", null);
                    NgutuFacebookAPI authAPI = ((NgutuUI) UI.getCurrent()).getFacebookAPI();
                    authAPI.setNavigationState(UI.getCurrent().getNavigator().getState());
                    UI.getCurrent().getSession().setAttribute("navigationState", UI.getCurrent().getNavigator().getState());
                    authAPI.deauthorize();
                }
            });
            menuActions.addComponent(facebookLogoutButton);
        }
    }
}
