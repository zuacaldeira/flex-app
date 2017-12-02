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
import db.FlexUser;
import view.footer.FlexFooter;

/**
 *
 * @author zua
 */
public class NewsView extends VerticalLayout implements View {

    private static final long serialVersionUID = 8467619842785075810L;

    private FlexUser user;
    private NewsMenu menu;
    private NewsBody body;
    private FlexFooter footer;

    public NewsView() {
    }
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        System.out.println("Enctering News VIEWS!!!!");
        initUser();
        initBody();
        initFooter();
        initMenu();
        super.addComponents(menu, body, footer);
        super.setExpandRatio(body, 1f);
        super.setStyleName("flex-view");
        super.setSizeFull();
        super.setMargin(false);
    }

    private void initUser() {
        if (UI.getCurrent() != null) {
            System.out.println("Found USER -> " + UI.getCurrent().getSession().getAttribute("user"));
            user = (FlexUser) UI.getCurrent().getSession().getAttribute("user");
            System.out.println("NEWS VIEW USER -> " + user);
        }
    }

    private void initMenu() {
        menu = new NewsMenu(user);
        menu.setHeight("48px");
    }

    private void initBody() {
        body = new NewsBody(user);
        body.setSizeFull();
    }

    private void initFooter() {
        footer = new FlexFooter(user);
        footer.setHeight("40px");
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


}
