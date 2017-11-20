/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.main;

import components.AdvertisementPanel;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.UI;
import view.body.NewsBody;
import com.vaadin.ui.VerticalLayout;
import db.FlexUser;
import ui.SecuredUI;
import view.menu.CanPopulate;
import view.footer.FlexFooter;
import view.menu.NewsMenu;

/**
 *
 * @author zua
 */
public class FlexNewsView extends VerticalLayout implements View, CanPopulate {

    private static final long serialVersionUID = 8467619842785075810L;

    private FlexUser user;
    private NewsMenu menu;
    private NewsBody body;
    private AdvertisementPanel ads;
    private FlexFooter footer;
    private int browserHeight;

    public FlexNewsView() {
    }

    private void initMenu() {
        menu = new NewsMenu(user);
        menu.setWidth("100%");
    }

    private void initBody() {
        body = new NewsBody(user);
        body.setSizeFull();
        /*if (Page.getCurrent() != null) {
            browserHeight = Page.getCurrent().getBrowserWindowHeight();
            body.setHeight(2 * browserHeight, Unit.PIXELS);
        }*/
    }

    private void initFooter() {
        footer = new FlexFooter(user);
    }

    private void initAds() {
        ads = new AdvertisementPanel("Barak Obama");
        ads.setHeight(browserHeight, Unit.PIXELS);
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
        //initAds();
        super.addComponents(menu, body, footer);
        super.setExpandRatio(body, 1f);
        super.setStyleName("flex-view");
        super.setSizeFull();
        super.setMargin(false);
        populate();
    }

}
