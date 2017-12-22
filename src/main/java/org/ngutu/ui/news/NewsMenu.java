/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import components.FlexButton;
import data.DataProviderType;
import org.ngutu.ui.viewproviders.FlexViews;

/**
 *
 * @author zua
 */
public class NewsMenu extends AbstractMenu {

    private static final long serialVersionUID = 8366211712669711650L;


    public NewsMenu() {
    }

    @Override
    public NewsBody getBody() {
        return ((NewsView) UI.getCurrent().getContent()).getBody();
    }


    @Override
    protected void search(String value) {
        Notification.show("Clicked on search " + value);
        UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS + "/" + DataProviderType.SEARCH + "/" + value);
    }

    @Override
    protected MenuActions createMenuActions() {
        MenuActions menuActions = new MenuActions();
        
        FlexButton menu = new FlexButton(null, VaadinIcons.MENU);
        menu.addClickListener(click -> {
            int xMax = Page.getCurrent().getBrowserWindowWidth();
            Window w = new Window(null, new NewsMenuBar());
            w.setWidthUndefined();
            w.setModal(true);
            w.center();
            w.setPositionY(8);
            w.setPositionX(xMax/2);            
            w.setClosable(true);
            w.setDraggable(true);
            UI.getCurrent().addWindow(w);
        });
        menuActions.addComponents(menu);
        return menuActions;
    }

    
}
