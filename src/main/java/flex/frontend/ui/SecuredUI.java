/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import flex.backend.news.db.FlexUser;
import flex.backend.news.services.FlexUserService;
import org.utils.ServiceLocator;

/**
 *
 * @author zua
 */
public abstract class SecuredUI extends UI {
    
    
    private FlexUser flexUser;

    @Override
    public void init(VaadinRequest request) {
        if(getSession() != null && getSession().getAttribute("username") != null) {
            FlexMainView mainView = createMainView();
            setContent(mainView);
            mainView.getActorRef().tell("load", null);
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
        addWindow(w);

        form.addLoginListener(event -> {
            FlexUserService userService = ServiceLocator.getInstance().findUserService();
            String username = event.getLoginParameter("username");
            String password = event.getLoginParameter("password");

            System.out.println("Username = " + username);
            System.out.println("Password = " + password);
            
            FlexUser dbUser = userService.login(new FlexUser(username, password));
            if(dbUser != null) {
                getSession().setAttribute("username", username);
                getSession().setAttribute("password", password);
                setFlexUser(dbUser);
            }
            w.close();
            Page.getCurrent().setLocation(getPageLocation());
        });
    }

    private void setFlexUser(FlexUser user) {
        this.flexUser = user;
    }
    
    protected abstract String getPageLocation();

    protected abstract FlexMainView createMainView();

    public FlexUser getFlexUser() {
        return flexUser;
    }

}
