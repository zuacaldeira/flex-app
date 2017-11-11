/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.main;

import ads.AdsFrame;
import ads.AmazonFrame;
import com.vaadin.server.Page;
import com.vaadin.server.Sizeable.Unit;
import ui.view.body.FlexBody;
import com.vaadin.ui.VerticalLayout;
import db.FlexUser;
import ui.view.menu.CanPopulate;
import ui.view.footer.FlexFooter;
import ui.view.menu.FlexMenu;

/**
 *
 * @author zua
 */
public class FlexMainView extends VerticalLayout implements CanPopulate {

    private static final long serialVersionUID = 8467619842785075810L;
    
    private final FlexUser user;
    private FlexMenu menu;
    private FlexBody body;
    private AdsFrame ads;
    private FlexFooter footer;
    private int browserHeight;

    public FlexMainView(FlexUser user) {
        this.user = user;
        initBody();
        initFooter();
        initMenu();
        initAds();
        super.addComponents(menu, body, ads, footer);
        super.setExpandRatio(body, 1f);
        super.setStyleName("flex-view");
        super.setSizeFull();
        super.setHeight(totalHeightInPixels(), Unit.PIXELS);
        super.setMargin(false);
    }

    private void initMenu() {
        menu = new FlexMenu(user);
        menu.setWidth("100%");
        menu.setHeight("64px");
    }

    private void initBody() {
        body = new FlexBody(user);
        body.setSizeFull();
        if(Page.getCurrent() != null) {
            browserHeight = Page.getCurrent().getBrowserWindowHeight();
            body.setHeight(2*browserHeight, Unit.PIXELS);
        }        
    }

    private void initFooter() {
        footer = new FlexFooter(user);
        footer.setHeight("128px");
    }
    
    private void initAds() {
        ads = new AmazonFrame();
        ads.setHeight(browserHeight, Unit.PIXELS);
    }
    
    
    public void setMenu(FlexMenu menu) {
        this.menu = menu;
    }
    
    
    public void setFooter(FlexFooter footer) {
        this.footer = footer;
    }

    public FlexMenu getMenu() {
        return menu;
    }

    public FlexBody getBody() {
        return body;
    }

    public FlexFooter getFooter() {
        return footer;
    }

    public void replaceBody(FlexBody flexBody) {
        replaceComponent(body, flexBody);
        this.body  = flexBody;
    }

    public FlexUser getUser() {
        return user;
    }

    @Override
    public void populate() {
        menu.populate();
        body.populate();
    }

    private float totalHeightInPixels() {
        return menu.getHeight() + body.getHeight() + ads.getHeight() + footer.getHeight();
    }

}
