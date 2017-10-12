/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import button.SaveButton;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import org.junit.Test;
import static org.junit.Assert.*;
import text.FlexTextField;

/**
 *
 * @author zua
 */
public class LoginFormTest {
    
    public LoginFormTest() {
    }

    @Test
    public void testCreate() {
        LoginForm form = new LoginForm();
        assertNotNull(form.getUsername());
        assertNotNull(form.getPassword());
        assertNotNull(form.getPassword2());
        assertNotNull(form.getRegister());
        assertNotNull(form.getRegisterOrSave());
        assertNotNull(form.getSaveButton());
    }
    
    /**
     * Test of getUsername method, of class LoginForm.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        LoginForm instance = new LoginForm();
        FlexTextField username = instance.getUsername();
        assertNotNull(username);
        username.setValue("username");
    }

    /**
     * Test of getPassword method, of class LoginForm.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        LoginForm instance = new LoginForm();
        PasswordField password = instance.getPassword();
        assertNotNull(password);
        password.setValue("Password");
    }

    /**
     * Test of getPassword2 method, of class LoginForm.
     */
    @Test
    public void testGetPassword2() {
        System.out.println("getPassword2");
        LoginForm instance = new LoginForm();
        PasswordField password2 = instance.getPassword2();
        assertNotNull(password2);
        password2.setValue("Password2");
    }

    /**
     * Test of getRegister method, of class LoginForm.
     */
    @Test
    public void testGetRegister() {
        System.out.println("getRegister");
        LoginForm instance = new LoginForm();
        CheckBox register = instance.getRegister();
        assertNotNull(register);
        register.setValue(true);
    }

    /**
     * Test of getSaveButton method, of class LoginForm.
     */
    @Test
    public void testGetSaveButton() {
        System.out.println("getSaveButton");
        LoginForm instance = new LoginForm();
        SaveButton save = instance.getSaveButton();
        assertNotNull(save);
        save.click();
    }

    /**
     * Test of getRegisterOrSave method, of class LoginForm.
     */
    @Test
    public void testGetRegisterOrSave() {
        System.out.println("getRegisterOrSave");
        LoginForm instance = new LoginForm();
        HorizontalLayout register = instance.getRegisterOrSave();
        assertNotNull(register);
    }
    
}
