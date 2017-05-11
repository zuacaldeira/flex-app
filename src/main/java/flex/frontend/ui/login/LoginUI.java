package flex.frontend.ui.login;

import javax.servlet.annotation.WebServlet;


import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@Push
@Theme("mytheme")
@SuppressWarnings("serial")
public class LoginUI extends UI {
    private LoginView loginView;

    @Override
    protected void init(VaadinRequest request) {
        loginView = new LoginView();
        setContent(loginView);
        
    }

    public LoginView getLoginView() {
        return loginView;
    }
    
    
	
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = LoginUI.class)
    public static class Servlet extends VaadinServlet {
    }
        
}