/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import data.DataProviderType;
import org.ngutu.ui.viewproviders.FlexViews;

/**
 *
 * @author zua
 */
public class NewsMenu extends AbstractMenu {

    private static final long serialVersionUID = 8366211712669711650L;


    public NewsMenu() {
        super();
        super.addComponentAsFirst(new NewsMenuBar());
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
        return menuActions;
    }

    
}
