/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import window.FlexWindow;
import view.FlexMainView;
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
    
    
    @Override
    public void init(VaadinRequest request) {
        if(getSession() != null && getSession().getAttribute("user") != null) {
            FlexMainView mainView = new FlexMainView((FlexUser)getSession().getAttribute("user"));
            setContent(mainView);
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

    protected abstract String getPageLocation();
}
