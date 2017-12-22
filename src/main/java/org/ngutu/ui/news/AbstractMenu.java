/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import components.FlexButton;
import db.auth.FlexUser;
import org.ngutu.ui.logo.FlexLogo;
import org.ngutu.ui.share.FacebookLoginButton;
import org.ngutu.ui.share.FacebookLogoutButton;

/**
 *
 * @author zua
 */
public abstract class AbstractMenu extends HorizontalLayout {

    private static final long serialVersionUID = 1394190815709078040L;

    private FlexLogo logo;
    private TextField searchBox;

    private HorizontalLayout actions;
    private FlexButton facebookButton;
    private Image picture;

    public static final String MENU_HEIGHT = "40px";
    private MenuBar menuBar;

    public AbstractMenu() {
        initLogo();
        initMenuBar();
        initActions();
        super.addComponents(logo, menuBar, actions);
        super.setComponentAlignment(logo, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(menuBar, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(actions, Alignment.MIDDLE_RIGHT);
        super.setSizeFull();
        super.setHeight("40px");
        super.setSpacing(true);
        super.setMargin(false);
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
        searchBox.setWidth(20, Unit.EM);
        searchBox.addValueChangeListener(e -> {
            search(e.getValue());
        });
    }

    protected abstract void search(String value);

    private void initMenuBar() {
        menuBar = createMenuBar();
    }

    private void initActions() {
        initSearchBox();
        initFacebookButtons();
        initPicture();
        actions = createMenuActions();
        actions.addComponent(searchBox);
        actions.addComponent(facebookButton);
        actions.addComponent(picture);
    }

    private void initPicture() {
        FlexUser user = getUser();
        if (user != null && user.getUserInfo() != null && user.getUserInfo().getPicture() != null) {
            picture = new Image(null, new ExternalResource(user.getUserInfo().getPicture()));
            picture.setWidth(MENU_HEIGHT);
            picture.setHeight(MENU_HEIGHT);
            picture.setStyleName("gravatar");
        }
        else {
            picture = new Image();
        }
        
        picture.addClickListener(click -> {
            Notification.show("TODO: Navigate to User Profile", Notification.Type.WARNING_MESSAGE);
        });
    }

    protected abstract MenuActions createMenuActions();
    protected abstract MenuBar createMenuBar();

    public abstract AbstractBody getBody();

    public final TextField getSearchBox() {
        return searchBox;
    }

    public HorizontalLayout getActions() {
        return actions;
    }

    public Image getPicture() {
        return picture;
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
    
    

    private void initFacebookButtons() {
        if (getUser() == null) {
            facebookButton = new FacebookLoginButton();
        } else {
            facebookButton = new FacebookLogoutButton();
        }
    }
}
