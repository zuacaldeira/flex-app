/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.login;

import com.vaadin.server.VaadinRequest;
import flex.frontend.ui.login.LoginUI.Servlet;
import flex.frontend.ui.news.UITest;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author zua
 */
public class LoginUITest extends UITest {
    
    public LoginUITest() {
    }

    /**
     * Test of init method, of class LoginUI.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        VaadinRequest request = Mockito.mock(VaadinRequest.class);
        LoginUI instance = new LoginUI();
        assertNull(instance.getLoginView());
        instance.init(request);
        assertNotNull(instance.getLoginView());
    }
    
    @Test
    public void testServlet() {
        Servlet instance = new LoginUI.Servlet();
        assertNotNull(instance);
        /*assertNotNull(instance.getServletConfig());
        assertNotNull(instance.getServletContext());
        assertNotNull(instance.getServletInfo());
        assertNotNull(instance.getServletName());*/
    }
    
}
