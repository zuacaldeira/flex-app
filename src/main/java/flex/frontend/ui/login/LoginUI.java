package flex.frontend.ui.login;

import javax.servlet.annotation.WebServlet;


import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

@Push
@Theme("mytheme")
@SuppressWarnings("serial")
public class LoginUI extends UI {
    private LoginView loginView;

    @Override
    protected void init(VaadinRequest request) {
        AbstractOrderedLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setSizeFull();
        setContent(layout);

        loginView = new LoginView();
        layout.addComponent(loginView);
        layout.setComponentAlignment(loginView, Alignment.MIDDLE_CENTER);
        
    }

    public LoginView getLoginView() {
        return loginView;
    }
    
    
	
    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = LoginUI.class)
    public static class Servlet extends VaadinServlet {
    }
        
}