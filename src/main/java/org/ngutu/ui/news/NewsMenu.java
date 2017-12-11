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
import db.auth.FlexUser;
import org.ngutu.ui.logo.FlexLogo;

/**
 *
 * @author zua
 */
public class NewsMenu extends HorizontalLayout {

    private static final long serialVersionUID = 8366211712669711650L;

    private FlexLogo logo;

    private TextField searchBox;

    private HorizontalLayout menuActions;
    private NewsMenuBar newsMenuBar;
    private Image picture;



    public NewsMenu() {
        initLogo();
        initSearchBox();
        initMenuActions();
        super.addComponents(logo, searchBox, menuActions);
        super.setComponentAlignment(logo, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(searchBox, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(menuActions, Alignment.MIDDLE_RIGHT);
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

    private void initMenuActions() {
        initMenuBar();
        initPicture();
        menuActions = new HorizontalLayout(newsMenuBar);
        menuActions.setMargin(false);
        menuActions.setSpacing(false);
        menuActions.setSizeUndefined();
        menuActions.setComponentAlignment(newsMenuBar, Alignment.MIDDLE_CENTER);
        if(picture != null) {
            menuActions.addComponent(picture);
            menuActions.setComponentAlignment(picture, Alignment.MIDDLE_CENTER);
        }
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
        newsMenuBar = new NewsMenuBar();
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
}
