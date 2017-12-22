/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.UI;
import components.FlexButton;
import org.ngutu.ui.news.AbstractMenu;
import org.ngutu.ui.news.MenuActions;
import org.ngutu.ui.viewproviders.FlexViews;

/**
 *
 * @author zua
 */
public class WelcomeMenu extends AbstractMenu {

    private static final long serialVersionUID = 8366211712669711650L;

    public WelcomeMenu() {
        getSearchBox().setVisible(false);
    }

    @Override
    protected void search(String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public WelcomeBody getBody() {
        return (WelcomeBody) ((WelcomeView) UI.getCurrent().getContent()).getBody();
    }
    
    @Override
    protected MenuActions createMenuActions() {        
        FlexButton newsButton = new FlexButton("Read the News", VaadinIcons.NEWSPAPER);
        newsButton.addClickListener(click -> {
            UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS);
        });
        
        MenuActions menuActions = new MenuActions();        
        menuActions.addComponent(newsButton);
        return menuActions;
    }
}
