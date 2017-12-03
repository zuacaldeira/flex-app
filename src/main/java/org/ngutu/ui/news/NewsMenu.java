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
import org.ngutu.ui.logo.FlexLogo;

/**
 *
 * @author zua
 */
public class NewsMenu extends HorizontalLayout {

    private static final long serialVersionUID = 8366211712669711650L;

    private FlexUser user;

    private FlexLogo logo;
    private HorizontalLayout actions;
    private TextField searchBox;
    private NewsMenuBar newsMenuBar;
    private Image picture;

    public NewsMenu(FlexUser user) {
        this.user = user;
        initLogo();
        initSearchBox();
        initActions();
        super.setSizeFull();
        super.setMargin(new MarginInfo(false, true, false, false));
        super.addComponents(logo, searchBox, actions);
        super.setComponentAlignment(logo, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(searchBox, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(actions, Alignment.MIDDLE_RIGHT);
        super.setStyleName("flex-menu");
    }

    private void initActions() {
        actions = new HorizontalLayout();
        actions.setMargin(new MarginInfo(false, true));
        actions.setSpacing(false);
        initMenuBar();
        initPicture();
    }

    private void initPicture() {
        if (user != null) {
            picture = new Image(null, new ExternalResource(user.getUserInfo().getPicture()));
            picture.setWidth("40px");
            picture.setHeight("40px");
            actions.addComponent(picture);
        }
    }

    private void initMenuBar() {
        newsMenuBar = new NewsMenuBar(user);
        actions.addComponent(newsMenuBar);
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

}
