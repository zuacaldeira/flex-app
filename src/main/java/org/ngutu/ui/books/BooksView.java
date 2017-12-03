/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.books;

import components.AdvertisementPanel;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import db.FlexUser;
import components.CanPopulate;
import view.footer.FlexFooter;

/**
 *
 * @author zua
 */
public class BooksView extends VerticalLayout implements View, CanPopulate {

    private static final long serialVersionUID = 3485805360419205354L;

    
    private FlexUser user;
    private BooksMenu menu;
    private BooksBody body;
    private AdvertisementPanel ads;
    private FlexFooter footer;
    private int browserHeight;

    public BooksView() {
    }

    private void initMenu() {
        menu = new BooksMenu();
        menu.setWidth("100%");
        menu.setHeight("64px");
    }

    private void initBody() {
        body = new BooksBody();
        body.setSizeFull();
        /*if (Page.getCurrent() != null) {
            browserHeight = Page.getCurrent().getBrowserWindowHeight();
            body.setHeight(2 * browserHeight, Unit.PIXELS);
        }*/
    }

    private void initFooter() {
        footer = new FlexFooter();
        footer.setHeight("64px");
    }

    private void initAds() {
        ads = new AdvertisementPanel("Barak Obama");
        ads.setHeight(browserHeight, Unit.PIXELS);
    }

    public void setMenu(BooksMenu menu) {
        this.menu = menu;
    }

    public void setFooter(FlexFooter footer) {
        this.footer = footer;
    }

    public BooksMenu getMenu() {
        return menu;
    }

    public BooksBody getBody() {
        return body;
    }

    public FlexFooter getFooter() {
        return footer;
    }

    public void replaceBody(BooksBody flexBody) {
        replaceComponent(body, flexBody);
        this.body = flexBody;
    }

    public FlexUser getUser() {
        return user;
    }

    @Override
    public void populate() {
        initUser();
        initBody();
        initFooter();
        initMenu();
        //initAds();
        super.addComponents(menu, body, footer);
        super.setExpandRatio(body, 1f);
        super.setStyleName("flex-view");
        super.setSizeFull();
        super.setMargin(false);
        if(menu != null) {
            menu.populate();
        }
        if(body != null) {
            body.populate();
        }
    }

    private void initUser() {
        if (UI.getCurrent() != null) {
            System.out.println("Found USER -> " + UI.getCurrent().getSession().getAttribute("user"));
            user = (FlexUser) UI.getCurrent().getSession().getAttribute("user");
            System.out.println("NEWS VIEW USER -> " + user);
        }
    }
    private float totalHeightInPixels() {
        return menu.getHeight() + body.getHeight() + footer.getHeight();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        populate();
    }

}
