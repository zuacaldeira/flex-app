/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.bantu;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import flex.backend.news.db.FlexUser;
import flex.backend.news.services.FlexUserService;
import flex.frontend.ui.FlexMainView;
import org.utils.ServiceLocator;

/**
 *
 * @author zua
 */
public abstract class SecuredUI extends UI {

    @Override
    public final void init(VaadinRequest request) {
        if(getSession() != null && getSession().getAttribute("username") != null) {
            setContent(createMainView());
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
            
            if(userService.login(new FlexUser(username, password)) != null) {
                getSession().setAttribute("username", username);
                getSession().setAttribute("password", password);
            }
            w.close();
            Page.getCurrent().setLocation(getPageLocation());
        });
    }
    
    protected abstract String getPageLocation();

    protected abstract FlexMainView createMainView();

}
