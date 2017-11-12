/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.menu;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import db.FlexUser;
import org.vaadin.addons.searchbox.SearchBox;
import ui.view.logo.FlexLogo;

/**
 *
 * @author zua
 */
public class FlexMenu extends HorizontalLayout implements CanPopulate {

    private static final long serialVersionUID = 8366211712669711650L;

    private final FlexUser user;

    private FlexLogo logo;
    private FlexMenuBar menuBar;
    private SearchBox searchBox;

    public FlexMenu(FlexUser user) {
        this.user = user;
        initLogo();
        initMenuBar();
        super.setSizeFull();
        super.setMargin(false);
        super.addComponents(logo, menuBar, new AddThisFrame());
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

    private void initLogo() {
        logo = new FlexLogo();
    }

    @Override
    public void populate() {
        menuBar.populate();
    }

}
