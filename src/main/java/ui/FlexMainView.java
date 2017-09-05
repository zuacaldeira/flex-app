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

    public FlexMainView(FlexUser user) {
        this.user = user;
        initBody();
        super.addComponent(body, "top:2cm; bottom:0cm");

        initFooter();
        super.addComponent(footer, "bottom:0%");

        initMenu();
        super.addComponent(menu, "top:0cm");

        super.setStyleName("flex-view");
        super.setSizeFull();
    }

    private void initMenu() {
        menu = new FlexMenu(user);
        menu.setWidth("100%");
        menu.setHeight("2cm");
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

}
