/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.ngutu.ui.adsense.AdSenseFrame;
import db.FlexUser;
import ui.SecuredUI;
import view.menu.CanPopulate;
import view.footer.FlexFooter;

/**
 *
 * @author zua
 */
public class NewsView extends VerticalLayout implements View, CanPopulate {

    private static final long serialVersionUID = 8467619842785075810L;

    private FlexUser user;
    private NewsMenu menu;
    private NewsBody body;
    private AdSenseFrame ads;
    private FlexFooter footer;
    private int browserHeight;

    public NewsView() {
    }

    private void initMenu() {
        menu = new NewsMenu(user);
        menu.setHeight("64px");
    }

    private void initBody() {
        body = new NewsBody(user);
        body.setSizeFull();
    }

    private void initFooter() {
        footer = new FlexFooter(user);
        footer.setHeight("48px");
    }

    private void initAds() {
        ads = new AdSenseFrame();
    }

    public void setMenu(NewsMenu menu) {
        this.menu = menu;
    }

    public void setFooter(FlexFooter footer) {
        this.footer = footer;
    }

    public NewsMenu getMenu() {
        return menu;
    }

    public NewsBody getBody() {
        return body;
    }

    public FlexFooter getFooter() {
        return footer;
    }

    public void replaceBody(NewsBody flexBody) {
        replaceComponent(body, flexBody);
        this.body = flexBody;
    }

    public FlexUser getUser() {
        return user;
    }

    @Override
    public void populate() {
        if(menu != null) {
            menu.populate();
        }
        if(body != null) {
            body.populate();
        }
    }

    private float totalHeightInPixels() {
        return menu.getHeight() + body.getHeight() + footer.getHeight();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        user = ((SecuredUI) UI.getCurrent()).getCurrentUser();
        initBody();
        initFooter();
        initMenu();
        initAds();
        super.addComponents(menu, body, footer);
        super.setExpandRatio(body, 1f);
        super.setStyleName("flex-view");
        super.setSizeFull();
        //super.setHeightUndefined();
        super.setMargin(false);
        populate();
    }

}
