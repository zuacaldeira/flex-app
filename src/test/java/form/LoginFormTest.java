/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

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
        assertNotNull(form.getPassword2());
        assertNotNull(form.getRegister());
        assertNotNull(form.getRegisterOrSave());
        assertNotNull(form.getSaveButton());
    }
    
}
