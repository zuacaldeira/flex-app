/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import db.auth.FlexUser;
import view.footer.FlexFooter;

/**
 *
 * @author zua
 */
public class WelcomeView extends VerticalLayout implements View {

    private static final long serialVersionUID = 8467619842785075810L;

    private FlexUser user;
    private WelcomeMenu menu;
    private WelcomeBody body;
    private FlexFooter footer;

    public WelcomeView() {
    }

    private void initMenu() {
        menu = new WelcomeMenu();
        menu.setHeight("48px");
    }

    private void initBody() {
        body = new WelcomeBody();
        body.setSizeFull();
    }

    private void initFooter() {
        footer = new FlexFooter();
        footer.setHeight("40px");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        initUser();
        initMenu();
        initBody();
        initFooter();
        super.addComponents(menu, body, footer);
        super.setExpandRatio(body, 1f);
        super.setStyleName("welcome-view");
        super.setSizeFull();
        super.setMargin(false);
    }

    private void initUser() {
        if (UI.getCurrent() != null) {
            user = (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        }
    }

    public void replaceBody(WelcomeBody flexBody) {
        replaceComponent(body, flexBody);
        this.body = flexBody;
    }

    public FlexUser getUser() {
        return user;
    }

}
