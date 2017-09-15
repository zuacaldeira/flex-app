/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import db.news.FlexUser;
import org.vaadin.addons.searchbox.SearchBox;
import utils.FlexUIUtils;

/**
 *
 * @author zua
 */
public class FlexMenu extends HorizontalLayout {

    private final FlexUser user;
    private final FlexMenuBar menuBar;
    private final LogoutButton logoutButton;
    private final SearchBox searchBox;
    
    public FlexMenu(FlexUser user) {
        this.user = user;
        this.menuBar = new FlexMenuBar(user);
                
        logoutButton = new LogoutButton();
        logoutButton.addUsername(user.getUsername());
        logoutButton.addClickListener(event -> {
            getSession().setAttribute("user", null);
            Page.getCurrent().setLocation("/flex-app");
        });
        //logoutButton.addUsername(user.getUsername());

        searchBox = new SearchBox(VaadinIcons.SEARCH, SearchBox.ButtonPosition.RIGHT);
        searchBox.setButtonJoined(true);
        searchBox.setWidth("100%");
        searchBox.setPlaceholder("Search news...");
        searchBox.setStyleName("searchbox");
        searchBox.getSearchButton().setStyleName("flex-button");
        searchBox.getSearchField().setStyleName("flex-text");
        searchBox.addSearchListener(e -> {
            Notification.show(e.getSearchTerm());
            FlexUIUtils.getInstance().getBody(this).updateData(DataProviderType.SEARCH, e.getSearchTerm());
        });
        
        super.setSizeFull();
        super.setHeight("2cm");
        super.setMargin(new MarginInfo(false, true));
        super.setMargin(false);
        
        super.addComponents(menuBar, searchBox, logoutButton);
        super.setComponentAlignment(menuBar, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(searchBox, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(logoutButton, Alignment.MIDDLE_RIGHT);
        super.setExpandRatio(menuBar, .5f);
        super.setExpandRatio(searchBox, .4f);
        super.setExpandRatio(logoutButton, .1f);
        super.setStyleName("flex-menu");
    }

    public FlexUser getUser() {
        return user;
    }

    public FlexMenuBar getMenuBar() {
        return menuBar;
    }
}
