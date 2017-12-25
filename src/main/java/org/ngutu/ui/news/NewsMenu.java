/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.ui.MenuBar;
import org.ngutu.ui.components.AbstractMenu;
import com.vaadin.ui.UI;
import components.HomeButton;

/**
 *
 * @author zua
 */
public class NewsMenu extends AbstractMenu {

    private static final long serialVersionUID = 8366211712669711650L;


    public NewsMenu() {
        super();
    }

    @Override
    public NewsBody getBody() {
        return ((NewsView) UI.getCurrent().getContent()).getBody();
    }


    @Override
    protected MenuActions createMenuActions() {
        MenuActions menuActions = new MenuActions();
        menuActions.addComponents(
                new SearchBox(),
                new HomeButton());
        return menuActions;
    }

    @Override
    protected MenuBar createMenuBar() {
        return new NewsMenuBar();
    }

}
