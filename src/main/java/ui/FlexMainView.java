/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.ui.AbsoluteLayout;
import db.news.FlexUser;

/**
 *
 * @author zua
 */
public class FlexMainView extends AbsoluteLayout {
    
    private final FlexUser user;
    private FlexMenu menu;
    private FlexBody body;
    private FlexFooter footer;
    private FlexLogo logo;

    public FlexMainView(FlexUser user) {
        this.user = user;
        initMenu();
        initBody();
        initFooter();
        initLogo();
        super.setStyleName("flex-view");
        super.setSizeFull();
        super.addComponent(body, getBodyStyle());
        super.addComponent(footer, getFooterStyle());
        super.addComponent(logo, getLogoStyle());
        super.addComponent(menu, getMenuStyle());
    }

    private void initMenu() {
        menu = new FlexMenu(user);
        menu.setWidth("100%");
        menu.setHeight("1cm");
    }

    private void initBody() {
        body = new FlexBody(user);
        body.setSizeFull();
    }

    private void initFooter() {
        footer = new FlexFooter(user);
        footer.setWidth("100%");
        footer.setHeight("1cm");
    }
    
    private void initLogo() {
        logo = new FlexLogo(user);
    }

    
    private String getBodyStyle() {
        return "top:2cm; bottom:1cm";
    }

    private String getFooterStyle() {
        return "bottom:0%";
    }
    
    private String getMenuStyle() {
        return "top:1cm";
    }

    private String getLogoStyle() {
        return "top:0%";
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

    public FlexLogo getLogo() {
        return logo;
    }
    
    
    
}
