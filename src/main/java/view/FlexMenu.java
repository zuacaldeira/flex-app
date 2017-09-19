/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import button.LogoutButton;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import db.FlexUser;
import org.vaadin.addons.searchbox.SearchBox;
import org.vaadin.addons.searchbox.SearchBox.ButtonPosition;
import data.DataProviderType;

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

        searchBox = new SearchBox("", ButtonPosition.HIDDEN);
        searchBox.setWidth("10cm");
        searchBox.setPlaceholder("Search news...");
        searchBox.addSearchListener(e -> {
            Notification.show(e.getSearchTerm());
            UIUtils.getInstance().getBody(this).updateData(DataProviderType.SEARCH, e.getSearchTerm());
        });
        
        HorizontalLayout others = new HorizontalLayout(searchBox, logoutButton);
        others.setSizeUndefined();
        others.setSpacing(false);
        
        super.setSizeFull();
        super.setHeight("2cm");
        super.setMargin(new MarginInfo(false, true));
        //super.setMargin(true);
        
        super.addComponents(menuBar, others);
        super.setComponentAlignment(menuBar, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(others, Alignment.MIDDLE_RIGHT);
        super.setExpandRatio(menuBar, .5f);
        super.setExpandRatio(others, .5f);
        super.setStyleName("flex-menu");
    }

    public FlexUser getUser() {
        return user;
    }

    public FlexMenuBar getMenuBar() {
        return menuBar;
    }
}
