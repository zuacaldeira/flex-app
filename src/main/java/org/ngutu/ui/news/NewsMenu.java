/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import data.DataProviderType;

/**
 *
 * @author zua
 */
public class NewsMenu extends AbstractMenu {

    private static final long serialVersionUID = 8366211712669711650L;


    public NewsMenu() {
    }

    @Override
    protected MenuBar createMenuBar() {
        return new NewsMenuBar();
    }

    private NewsBody getBody() {
        return ((NewsView) UI.getCurrent().getContent()).getBody();
    }


    @Override
    protected void search(String value) {
        Notification.show("Clicked on search " + value);
        NewsBody body = getBody();
        body.populate(DataProviderType.SEARCH, value);
    }

    @Override
    public void populate() {
        //
    }

    
}
