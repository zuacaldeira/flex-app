/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.login;

import services.news.FlexUserService;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import db.news.FlexUser;
import ui.SaveButton;
import ui.news.FlexTextField;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class LoginForm extends VerticalLayout {

    private final FormLayout formLayout;
    private final FlexTextField username;
    private final PasswordField password;
    private final PasswordField password2;
    private final CheckBox register;
    private final SaveButton saveButton;

    public LoginForm() {
        formLayout = new FormLayout();
        
        username = new FlexTextField("Username", VaadinIcons.USER);
        username.setSizeFull();
        username.setRequiredIndicatorVisible(true);
        
        password = new PasswordField("Password");
        password.setSizeFull();
        password.setIcon(VaadinIcons.LOCK);
        password.setRequiredIndicatorVisible(true);
        
        password2 = new PasswordField("Password 2");
        password2.setSizeFull();
        password2.setIcon(VaadinIcons.LOCK);
        password2.setRequiredIndicatorVisible(true);
        
        register = new CheckBox("Not a member? Check to register", false);
        register.addValueChangeListener(event -> {
                password2.setVisible(event.getValue());
                if(!event.getValue()) {
                    password2.clear();
            }
        });
        saveButton = new SaveButton();
        saveButton.addClickListener(event -> {
            FlexUserService userService = ServiceLocator.getInstance().findUserService();
            String u = this.username.getValue().trim();
            String p = this.password.getValue().trim();

            System.out.println("Username = " + u);
            System.out.println("Password = " + p);
            
            FlexUser dbUser = null;
            if(!register.isEmpty()) {
                if(!existsUserNamed(u)) {
                    registerNewUser(u, p);
                    loginUser(u, p);
                }
                else {
                    this.username.clear();
                    this.password.clear();
                    this.password2.clear();
                }
            }
            else {
                loginUser(u, p);
            }
        });
        formLayout.addComponents(username, password, password2, register, saveButton);
        password2.setVisible(false);
        formLayout.setSizeFull();
        formLayout.setSpacing(true);
        formLayout.setMargin(true);
        addComponent(formLayout);
    }

    public FormLayout getFormLayout() {
        return formLayout;
    }

    private boolean existsUserNamed(String username) {
        FlexUserService service = ServiceLocator.getInstance().findUserService();
        return service.contains(new FlexUser(username, null));
    }

    private void loginUser(String user, String pass) {
        FlexUserService service = ServiceLocator.getInstance().findUserService();
        FlexUser dbUser = service.login(new FlexUser(user, pass));
        if(dbUser != null) {
            getSession().setAttribute("user", dbUser);
            ((Window)getParent()).close();
            Page.getCurrent().setLocation("/flex-app/news");
        }
    }

    private FlexUser registerNewUser(String username, String password) {
        System.out.println("Register = " + register.getValue());
        if(this.password.getValue().equals(password2.getValue())) {
            FlexUserService service = ServiceLocator.getInstance().findUserService();
            return service.register(new FlexUser(username, password));
        }
        else {
            password2.clear();
            return null;
        }
    }

    
    
}
