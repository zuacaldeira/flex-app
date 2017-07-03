/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import db.news.FlexUser;
import ui.login.LoginForm;

/**
 *
 * @author zua
 */
public abstract class SecuredUI extends UI {
    
    
    private FlexUser flexUser;

    @Override
    public void init(VaadinRequest request) {
        if(getSession() != null && getSession().getAttribute("user") != null) {
            setFlexUser((FlexUser)getSession().getAttribute("user"));
            FlexMainView mainView = new FlexMainView();
            setContent(mainView);
        }        
        else {
            login();
        }
    }
    
    private void login() {
        LoginForm form = new LoginForm();
        Window w = new Window("Login", form);
        w.center();
        w.setModal(true);
        w.setWidth("50%");
        w.setHeight("75%");
        w.setClosable(false);
        addWindow(w);
    }

    private void setFlexUser(FlexUser user) {
        this.flexUser = user;
    }
    
    protected abstract String getPageLocation();

    public FlexUser getFlexUser() {
        return flexUser;
    }

}
