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
import backend.services.auth.FlexUserServiceInterface;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class RegisterForm extends FlexForm {

    private static final long serialVersionUID = 5363531124855151443L;

    private FlexUserServiceInterface service;
    private FlexUser user;

    private final Binder<FlexUser> binder;
    private FlexTextField username;
    private PasswordField password;
    private PasswordField password2;
    private FlexButton registerButton;

    public RegisterForm() {
        binder = new Binder<>();
        user = new FlexUser();
        initUsername();
        initPassword();
        initPassword2();
        initRegisterButton();
        service = ServiceLocator.getInstance().findUserService();
        super.addComponents(username, password, password2, registerButton);
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
                .withValidator(u -> !existsUserNamed(u), "There is already a user registered with this email")
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

    private void initPassword2() {
        password2 = new PasswordField("Password 2");
        password2.setSizeFull();
        password2.setIcon(VaadinIcons.LOCK);
        password2.setRequiredIndicatorVisible(true);
        binder.forField(password2)
                .asRequired("Password 2 is missing. Please choose a pasword")
                .withValidator(pass -> pass.equals(password.getValue()), "Passwords must be equal")
                .bind(FlexUser::getPassword, FlexUser::setPassword);
    }

    private void initRegisterButton() {
        registerButton = new FlexButton("Register", VaadinIcons.CHECK);
        registerButton.addClickListener(event -> {
            try {
                binder.writeBean(user);
                registerButton.setEnabled(false);
                user = ServiceLocator.getInstance().findUserService().register(user.getUsername(), user.getPassword());
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

    public PasswordField getPassword2() {
        return password2;
    }

}
