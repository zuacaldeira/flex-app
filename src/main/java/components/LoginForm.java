/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import org.ngutu.ui.components.FlexForm;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.UI;
import db.auth.FlexUser;
import org.ngutu.ui.viewproviders.FlexViews;
import backend.services.auth.FlexUserService;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class LoginForm extends FlexForm {

    private static final long serialVersionUID = 5363531124855151443L;

    private FlexUserService service;
    private FlexUser user;

    private Binder<FlexUser> binder;

    private FlexTextField username;
    private PasswordField password;
    private SaveButton loginButton;

    public LoginForm() {
        binder = new Binder<>();
        user = new FlexUser();
        initUsername();
        initPassword();
        initSaveButton();
        service = ServiceLocator.getInstance().findUserService();
        super.addComponents(username, password, loginButton);
        super.setHeightUndefined();
        super.setWidth("50%");
        super.setSpacing(true);
        super.setMargin(true);
        super.setStyleName("login-form");
        username.focus();
        setSizeFull();
    }

    private boolean existsUserNamed(String username) {
        return service.findUserNamed(username) != null;
    }

    private void initUsername() {
        username = new FlexTextField("Username", VaadinIcons.USER);
        username.setSizeFull();
        username.setCaptionAsHtml(true);
        username.setValueChangeTimeout(2000);
        username.setRequiredIndicatorVisible(true);
        binder.forField(username)
                .asRequired("Username missing. Please enter your email address")
                .withValidator(new EmailValidator("Username must be a valid email."))
                .withValidator(u -> existsUserNamed(u), "There is no user registered with this email")
                .bind(FlexUser::getUsername, FlexUser::setUsername);
    }

    private void initPassword() {
        password = new PasswordField("Password");
        password.setSizeFull();
        password.setCaptionAsHtml(true);
        password.setIcon(VaadinIcons.LOCK);
        password.setRequiredIndicatorVisible(true);
        binder.forField(password)
                .asRequired("Password missing. Please choose a pasword")
                .withValidator(pass -> pass.length() >= 8, "Passwords must have at least 8 characaters")
                .bind(FlexUser::getPassword, FlexUser::setPassword);
    }


    private void initSaveButton() {
        loginButton = new SaveButton();
        loginButton.addClickListener(event -> {
            try {
                binder.writeBean(user);
                loginButton.setEnabled(false);
                user = service.login(user.getUsername(), user.getPassword());
                getSession().setAttribute("user", user);
                UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS);
            } catch (ValidationException ex) {
                Notification.show("User could not be created and saved. Please check error messages for each field.");
            }
        });
    }

    public FlexTextField getUsername() {
        return username;
    }

    public PasswordField getPassword() {
        return password;
    }

    public SaveButton getLoginButton() {
        return loginButton;
    }

}
