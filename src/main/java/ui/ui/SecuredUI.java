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
import java.net.URI;

/**
 *
 * @author zua
 */
public abstract class SecuredUI extends UI {

    private String pageLocation;
    
    
    @Override
    public void init(VaadinRequest request) {
        if(getSession() != null && getSession().getAttribute("user") != null) {
            pageLocation = Page.getCurrent().getLocation().getPath();
            FlexMainView mainView = new FlexMainView((FlexUser)getSession().getAttribute("user"));
            setContent(mainView);
            mainView.populate();
        }        
        else {
            login();
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

    protected String getPageLocation() {
        return pageLocation;
    }
}
