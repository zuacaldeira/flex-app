/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Window;
import db.FlexUser;
import services.FlexUserServiceInterface;
import button.SaveButton;
import text.FlexTextField;
import ui.ui.FlexAppUI;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class LoginForm extends FlexForm {

    private FlexTextField username;
    private PasswordField password;
    private PasswordField password2;
    private CheckBox register;
    private SaveButton saveButton;
    private FlexUserServiceInterface service;
    private HorizontalLayout registerOrSave;

    public LoginForm() {
        initUsername();
        initPassword();
        initPassword2();
        initRegisterCheckBox();
        initSaveButton();
        initRegisterOrSave();
        service = ServiceLocator.getInstance().findUserService();
        addComponents(username, password, password2, registerOrSave);
        setSizeFull();
        setHeightUndefined();
        setSpacing(true);
        setMargin(true);
        username.focus();
    }

    private boolean existsUserNamed(String username) {
        return service.findUserNamed(username) != null;
    }

    private void saveToLogin(String user, String pass) {
        FlexUser dbUser = service.login(user, pass);
        if (dbUser != null && getSession() != null) {
            getSession().setAttribute("user", dbUser);
            ((Window) getParent()).close();
            Page.getCurrent().setLocation(((FlexAppUI) getUI()).getPageLocation());
        }
    }

    private FlexUser registerNewUser(String username, String password) {
        if (this.password.getValue().equals(password2.getValue())) {
            return service.register(username, password);
        } else {
            password2.clear();
            return null;
        }
    }

    private void initUsername() {
        username = new FlexTextField("Username", VaadinIcons.USER);
        username.setSizeFull();
        username.setCaptionAsHtml(true);
        username.setValueChangeTimeout(2000);
        username.setRequiredIndicatorVisible(true);
    }

    private void initPassword() {
        password = new PasswordField("Password");
        password.setSizeFull();
        password.setCaptionAsHtml(true);
        password.setIcon(VaadinIcons.LOCK);
        password.setRequiredIndicatorVisible(true);
        password.addValueChangeListener(e -> {
            //saveButton.setEnabled(!password.getValue().isEmpty() && existsUserNamed(username.getValue()));
        });
    }

    private void initPassword2() {
        password2 = new PasswordField("Password 2");
        password2.setSizeFull();
        password2.setIcon(VaadinIcons.LOCK);
        password2.setRequiredIndicatorVisible(true);
        password2.setVisible(false);
        password2.addValueChangeListener(e -> {
            //saveButton.setEnabled(!e.getValue().isEmpty() && e.getValue().equals(password.getValue()));
        });
    }

    private void initRegisterCheckBox() {
        register = new CheckBox("Not yet a member?", false);
        register.setSizeUndefined();
        register.setDescription("Register / Join us");
        register.addValueChangeListener(event -> {
            if (!event.getValue()) {          // Unchecked
                password2.clear();           // Clear password 2
                password2.setVisible(false); // Hide it
                password.focus();            // Focus on password
            } else {
                password2.setVisible(event.getValue());
                password2.focus();
            }
        });
    }

    private void initSaveButton() {
        saveButton = new SaveButton();
        saveButton.addClickListener(event -> {
            String u = this.username.getValue().trim();
            String p = this.password.getValue().trim();
            FlexUser dbUser = null;
            if (!register.isEmpty()) {
                saveToRegister(u, p);
            } else {
                saveToLogin(u, p);
            }
        });
        //saveButton.setEnabled(true);
    }

    private void saveToRegister(String username, String password) {
        if (!existsUserNamed(username)) {
            FlexUser dbUser = registerNewUser(username, password);
            if (dbUser != null) {
                saveToLogin(dbUser.getUsername(), password);
            }
        } else {
            this.username.clear();
            this.password.clear();
            this.password2.clear();
        }
    }

    private void initRegisterOrSave() {
        registerOrSave = new HorizontalLayout(register, saveButton);
        registerOrSave.setSizeFull();
        //registerOrSave.setHeightUndefined();
        registerOrSave.setSpacing(true);
        registerOrSave.setMargin(false);
        registerOrSave.setComponentAlignment(register, Alignment.MIDDLE_LEFT);
        registerOrSave.setComponentAlignment(saveButton, Alignment.MIDDLE_RIGHT);
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

    public CheckBox getRegister() {
        return register;
    }

    public SaveButton getSaveButton() {
        return saveButton;
    }

    public HorizontalLayout getRegisterOrSave() {
        return registerOrSave;
    }
    
    
}
