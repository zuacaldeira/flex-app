/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.login;

import flex.frontend.ui.MainView;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class LoginViewTest {
    
    public LoginViewTest() {
    }

    @Test
    public void testLoginView() {
        LoginView loginView = new LoginView();
        assertTrue(loginView instanceof MainView);
    }
    
}
