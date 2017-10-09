/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.menu;

import button.LogoutButton;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import data.DataProviderType;
import db.FlexUser;
import org.vaadin.addons.searchbox.SearchBox;
import org.vaadin.addons.searchbox.SearchBox.ButtonPosition;
import ui.view.logo.FlexLogo;
import utils.UIUtils;

/**
 *
 * @author zua
 */
public class FlexMenu extends HorizontalLayout implements CanPopulate {

    private final FlexUser user;

    private FlexLogo logo;
    private FlexMenuBar menuBar;
    private SearchBox searchBox;

    public FlexMenu(FlexUser user) {
        this.user = user;
        initLogo();
        initMenuBar();
        initSearchBox();
        super.setSizeFull();
        super.setMargin(false);
        super.addComponents(menuBar);
        super.setComponentAlignment(menuBar, Alignment.MIDDLE_CENTER);
        super.setStyleName("flex-menu");
    }

    public FlexUser getUser() {
        return user;
    }

    public FlexLogo getLogo() {
        return logo;
    }

    public FlexMenuBar getMenuBar() {
        return menuBar;
    }

    private void initMenuBar() {
        this.menuBar = new FlexMenuBar(user);
    }

    private void initSearchBox() {
        searchBox = new SearchBox(VaadinIcons.SEARCH, ButtonPosition.RIGHT);
        searchBox.addSearchListener(e -> {
            Notification.show("Clicked on search " + e.getSearchTerm());
            UIUtils.getInstance().getBody(this).updateData(DataProviderType.SEARCH, e.getSearchTerm());
        });
    }

    private void initLogo() {
        logo = new FlexLogo();
    }

    @Override
    public void populate() {
        menuBar.populate();
    }

    public SearchBox getSearchBox() {
        return searchBox;
    }

}
