/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.UI;
import org.ngutu.ui.common.FlexButton;
import org.ngutu.ui.common.AbstractMenu;
import org.ngutu.ui.news.FlexMenuButton;
import org.ngutu.ui.news.MenuActions;
import org.ngutu.ui.viewproviders.FlexViews;

/**
 *
 * @author zua
 */
public class WelcomeMenu extends AbstractMenu {

    private static final long serialVersionUID = 8366211712669711650L;

    public WelcomeMenu() {
    }

    @Override
    public WelcomeBody getBody() {
        return (WelcomeBody) ((WelcomeView) UI.getCurrent().getContent()).getBody();
    }
    
    @Override
    protected MenuActions createMenuActions() {        
        FlexButton newsButton = new FlexMenuButton("Read the News", VaadinIcons.NEWSPAPER);
        newsButton.addClickListener(click -> {
            UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS  + "/latest");
        });
        
        MenuActions menuActions = new MenuActions();        
        menuActions.addComponents(newsButton, new UserLayout());
        return menuActions;
    }

}
