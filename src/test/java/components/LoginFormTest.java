/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import com.vaadin.ui.PasswordField;
import org.junit.Test;
import static org.junit.Assert.*;

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
        assertNotNull(form.getLoginButton());
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
     * Test of getSaveButton method, of class LoginForm.
     */
    @Test
    public void testGetSaveButton() {
        System.out.println("getSaveButton");
        LoginForm instance = new LoginForm();
        instance.getUsername().setValue("username");
        instance.getPassword().setValue("pass");
        SaveButton save = instance.getLoginButton();
        assertNotNull(save);
    }
}
