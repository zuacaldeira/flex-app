/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.login;

import flex.backend.news.services.FlexUserService;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.VerticalLayout;
import flex.backend.news.db.FlexUser;
import flex.frontend.ui.news.FlexTextField;
import org.utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class LoginForm extends VerticalLayout implements ClickListener {

    private final FormLayout formLayout;
    private final FlexTextField username;
    private final FlexTextField password;

    public LoginForm() {
        formLayout = new FormLayout();
        username = new FlexTextField("Username", VaadinIcons.USER);
        password = new FlexTextField("Password", VaadinIcons.LOCK);
        formLayout.addComponents(username, password);
        formLayout.setWidth("200px");
        formLayout.setHeightUndefined();
        addComponent(formLayout);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if(validateCredentials(this.username.getValue(), this.password.getValue())) {
            FlexUser user = login(this.username.getValue(), this.password.getValue());
            Page.getCurrent().setLocation("/flex-app/news");
        }
    }

    public FormLayout getFormLayout() {
        return formLayout;
    }

    private boolean validateCredentials(String user, String pass) {
        FlexUserService service = ServiceLocator.getInstance().findUserService();
        return service.contains(new FlexUser(user, pass));
    }

    private FlexUser login(String user, String pass) {
        FlexUserService service = ServiceLocator.getInstance().findUserService();
        return service.login(new FlexUser(user, pass));
    }
    
    
    
}
