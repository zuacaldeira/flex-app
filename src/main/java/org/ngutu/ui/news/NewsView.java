/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;
import data.DataProviderType;
import db.auth.FlexUser;

/**
 *
 * @author zua
 */
public class NewsView extends AbstractView {

    private static final long serialVersionUID = 8467619842785075810L;


    public NewsView() {
    }
    
    public void replaceBody(NewsBody flexBody) {
        replaceComponent(getBody(), flexBody);
    }

    private FlexUser getUser() {
        if (UI.getCurrent() != null) {
            System.out.println("Found USER -> " + UI.getCurrent().getSession().getAttribute("user"));
            return (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        }
        return null;
    }

    @Override
    protected NewsMenu createMenu() {
        return new NewsMenu();
    }

    @Override
    protected NewsBody createBody() {
        return new NewsBody();
    }

    @Override
    public NewsBody getBody() {
        return (NewsBody) super.getBody(); 
    }

    @Override
    public NewsMenu getMenu() {
        return (NewsMenu) super.getMenu(); 
    }
    
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        System.out.println("Enctering News VIEWS!!!!");
        getMenu().populate();
        getBody().populate(DataProviderType.LATEST, null);
    }

}
