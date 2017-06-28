/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Notification;
import flex.backend.news.db.FlexUser;
import flex.backend.news.db.NewsArticle;
import org.utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class FlexMainView extends AbsoluteLayout {
    
    private FlexMenu menu;
    private FlexBody body;
    private FlexFooter footer;
    

    public FlexMainView() {
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
        menu = new FlexMenu();
        menu.setWidth("100%");
        menu.setHeight(getTopPosition());
    }

    private void initBody() {
        body = new FlexBody();
        body.setSizeFull();
    }

    private void initFooter() {
        footer = new FlexFooter();
        footer.setWidth("100%");
        footer.setHeight("1cm");
    }

    
    private String getBodyStyle() {
        return "top:" + getTopPosition() + "; " + "bottom:" + getBottomPosition();
    }

    private String getTopPosition() {
        return "1.5cm";
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
        Notification.show("Body Set");
    }
    
    public FlexUser getFlexUser() {
        if(getUI() instanceof SecuredUI) {
            return ((SecuredUI) getUI()).getFlexUser();
        }
        return null;
    }

}
