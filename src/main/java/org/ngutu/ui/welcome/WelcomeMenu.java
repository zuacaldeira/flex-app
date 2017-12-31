/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import components.FlexButton;
import org.ngutu.ui.components.AbstractMenu;
import org.ngutu.ui.news.MenuActions;
import org.ngutu.ui.viewproviders.FlexViews;

/**
 *
 * @author zua
 */
public class WelcomeMenu extends AbstractMenu {

    private static final long serialVersionUID = 8366211712669711650L;

    public WelcomeMenu() {
        super.getMenuBar().setVisible(false);
    }

    @Override
    public WelcomeBody getBody() {
        return (WelcomeBody) ((WelcomeView) UI.getCurrent().getContent()).getBody();
    }
    
    @Override
    protected MenuActions createMenuActions() {        
        FlexButton newsButton = new FlexButton("Read the News", VaadinIcons.NEWSPAPER);
        newsButton.addClickListener(click -> {
            UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS  + "/latest");
        });
        
        MenuActions menuActions = new MenuActions();        
        menuActions.addComponent(newsButton);
        return menuActions;
    }

    @Override
    protected MenuBar createMenuBar() {
        return new WelcomeMenuBar();
    }
}
