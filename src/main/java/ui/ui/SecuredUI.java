/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.ui;

import com.vaadin.server.Page;
import window.FlexWindow;
import ui.view.main.FlexMainView;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import db.FlexUser;
import form.LoginForm;

/**
 *
 * @author zua
 */
public abstract class SecuredUI extends UI {

    private static final long serialVersionUID = 2637212442082775079L;

    private String pageLocation;

    @Override
    public void init(VaadinRequest request) {
        if (getSession() != null) {
            if (getSession().getAttribute("user") != null) {
                pageLocation = Page.getCurrent().getLocation().getPath() + "/news";
                FlexMainView mainView = new FlexMainView((FlexUser) getSession().getAttribute("user"));
                setContent(mainView);
                mainView.populate();
            } else {
                pageLocation = Page.getCurrent().getLocation().getPath();
                login();
            }
        }
    }

    private void login() {
        LoginForm form = new LoginForm();
        Window w = new FlexWindow("Login", form);
        w.center();
        w.setModal(true);
        w.setWidth("50%");
        w.setHeightUndefined();
        w.setClosable(false);
        addWindow(w);
    }

    public String getPageLocation() {
        return pageLocation;
    }
}
