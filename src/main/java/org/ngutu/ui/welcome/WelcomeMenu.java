/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

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
import org.ngutu.ui.logo.FlexLogo;
import org.ngutu.ui.news.NewsBody;
import org.ngutu.ui.news.NewsView;
import org.ngutu.ui.share.FacebookLoginButton;
import org.ngutu.ui.share.FacebookLogoutButton;

/**
 *
 * @author zua
 */
public class WelcomeMenu extends HorizontalLayout {

    private static final long serialVersionUID = 8366211712669711650L;

    private FlexLogo logo;
    private HorizontalLayout center;
    private TextField searchBox;
    private HorizontalLayout userLayout;
    private Image picture;
    private FacebookLoginButton facebookLogin;
    private FacebookLogoutButton facebookLogout;
    private HorizontalLayout right;
    private WelcomeMenuBar welcomeMenuBar;

    public WelcomeMenu() {
        initLogo();
        initCenter();
        initRight();
        super.addComponents(logo, center, right);
        super.setComponentAlignment(logo, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(center, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(right, Alignment.MIDDLE_RIGHT);
        super.setExpandRatio(center, 1f);
        super.setSizeFull();
        super.setSpacing(true);
        super.setMargin(new MarginInfo(false, true, false, false));
        super.setStyleName("flex-menu");
    }

    private FlexUser getUser() {
        if (UI.getCurrent() != null) {
            System.out.println("Found USER -> " + UI.getCurrent().getSession().getAttribute("user"));
            return (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        }
        return null;
    }

    private void initUserLayout() {
        userLayout = new HorizontalLayout();
        userLayout.setSpacing(true);
        initFacebookButton();
        initPicture();
    }
    
    private void initPicture() {
        FlexUser user = getUser();
        if (user != null && user.getUserInfo() != null && user.getUserInfo().getPicture() != null) {
            picture = new Image(null, new ExternalResource(user.getUserInfo().getPicture()));
            picture.setWidth("32px");
            picture.setHeight("32px");
            picture.setStyleName("circle gravatar");
            userLayout.addComponent(picture);
        }
    }

    private void initMenuBar() {
        welcomeMenuBar = new WelcomeMenuBar();
    }

    private void initSearchBox() {
        searchBox = new TextField();
        searchBox.setCaptionAsHtml(true);
        searchBox.setPlaceholder("Search");
        searchBox.setStyleName("search-box");
        searchBox.setWidth("100%");
        searchBox.addValueChangeListener(e -> {
            Notification.show("Clicked on search " + e.getValue());
            NewsBody body = getBody();
            body.populate(DataProviderType.SEARCH, e.getValue());
        });
    }

    private NewsBody getBody() {
        return ((NewsView) UI.getCurrent().getContent()).getBody();
    }

    public FlexLogo getLogo() {
        return logo;
    }

    private void initLogo() {
        logo = new FlexLogo();
    }

    private void initFacebookButton() {
        if(getUser() == null) {
            facebookLogin = new FacebookLoginButton();
            userLayout.addComponent(facebookLogin);
            userLayout.setComponentAlignment(facebookLogin, Alignment.MIDDLE_CENTER);
        }
        else {
            facebookLogout = new FacebookLogoutButton();
            userLayout.addComponent(facebookLogout);
            userLayout.setComponentAlignment(facebookLogout, Alignment.MIDDLE_CENTER);
        }
    }

    private void initCenter() {
        initSearchBox();
        initUserLayout();
        center = new HorizontalLayout(searchBox, userLayout);
        center.setMargin(false);
        center.setSpacing(true);
        center.setWidth("75%");
        center.setExpandRatio(searchBox, 1f);
        center.setComponentAlignment(searchBox, Alignment.MIDDLE_CENTER);
        center.setComponentAlignment(userLayout, Alignment.MIDDLE_CENTER);
    }

    private void initRight() {
        initMenuBar();
        right = new HorizontalLayout(welcomeMenuBar);
        right.setMargin(false);
        right.setSpacing(false);
    }

}
