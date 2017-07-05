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
    
    private FlexMenu menu;
    private FlexBody body;
    private FlexFooter footer;
    private FlexUser user;

    public FlexMainView(FlexUser user) {
        this.user = user;
        initMenu();
        initBody();
        initFooter();
        setStyleName("flex-view");
        setSizeFull();
        addComponent(body, getBodyStyle());
        addComponent(footer, getFooterStyle());
        addComponent(menu, getMenuStyle());
    }

    private void initMenu() {
        menu = new FlexMenu(user);
        menu.setWidth("100%");
        menu.setHeight(getTopPosition());
    }

    private void initBody() {
        body = new FlexBody(user);
        body.setSizeFull();
    }

    private void initFooter() {
        footer = new FlexFooter(user);
        footer.setWidth("100%");
        footer.setHeight(getBottomPosition());
    }

    
    private String getBodyStyle() {
        return "top:" + getTopPosition() + "; " + "bottom:" + getBottomPosition();
    }

    private String getTopPosition() {
        return "1cm";
    }
    
    private String getBottomPosition() {
        return "1cm";
    }
    
    private String getFooterStyle() {
        return "bottom:0%";
    }
    
    private String getMenuStyle() {
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
    
    
    
}
