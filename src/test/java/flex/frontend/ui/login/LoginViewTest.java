/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.login;

import flex.frontend.ui.FlexMainView;
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
        assertTrue(loginView instanceof FlexMainView);
    }
    
    @Test
    public void testExistenceOfFormlayout() {
        LoginView loginView = new LoginView();
        assertNotNull((WelcomeView)loginView.getBody());
    }
}
