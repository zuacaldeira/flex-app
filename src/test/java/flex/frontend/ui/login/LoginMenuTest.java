/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.login;

import com.vaadin.ui.Button;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class LoginMenuTest {
    
    public LoginMenuTest() {
    }

    /**
     * Test of getNewsButton method, of class LoginMenu.
     */
    @Test
    public void testGetNewsButton() {
        System.out.println("getNewsButton");
        LoginMenu instance = new LoginMenu();
        assertNotNull(instance.getNewsButton());
    }

    @Test
    public void testGetNewsButtonClickListner() {
        System.out.println("testGetNewsButtonClickListner");
        LoginMenu instance = new LoginMenu();
        assertNotNull(instance.getNewsButton());
        assertFalse(instance.getNewsButton().getListeners(Button.ClickEvent.class).isEmpty());
    }

    /**
     * Test of getBantusButton method, of class LoginMenu.
     */
    @Test
    public void testGetBantusButton() {
        System.out.println("getBantusButton");
        LoginMenu instance = new LoginMenu();
        assertNotNull(instance.getBantusButton());
    }

    @Test
    public void testGetBantusButtonClickListner() {
        System.out.println("testGetBantusButtonClickListner");
        LoginMenu instance = new LoginMenu();
        assertNotNull(instance.getBantusButton());
        assertFalse(instance.getBantusButton().getListeners(Button.ClickEvent.class).isEmpty());
    }

    /**
     * Test of getAboutMeButton method, of class LoginMenu.
     */
    @Test
    public void testGetAboutMeButton() {
        System.out.println("getAboutMeButton");
        LoginMenu instance = new LoginMenu();
        assertNotNull(instance.getAboutMeButton());
    }
 
    @Test
    public void testGetAboutMeButtonClickListner() {
        System.out.println("testGetAboutMeButtonClickListner");
        LoginMenu instance = new LoginMenu();
        assertNotNull(instance.getAboutMeButton());
        assertFalse(instance.getAboutMeButton().getListeners(Button.ClickEvent.class).isEmpty());
    }

    @Test(expected = Exception.class)
    public void testAboutMeButtonClick() {
        System.out.println("testGetAboutMeButtonClickListner");
        LoginMenu instance = new LoginMenu();
        assertNotNull(instance.getAboutMeButton());
        instance.getAboutMeButton().click();
    }

    @Test(expected = Exception.class)
    public void testNewsButtonClick() {
        System.out.println("testNewsButtonClick");
        LoginMenu instance = new LoginMenu();
        assertNotNull(instance.getNewsButton());
        instance.getNewsButton().click();
    }
    
    @Test(expected = Exception.class)
    public void testBantusClick() {
        System.out.println("testBantusButtonClick");
        LoginMenu instance = new LoginMenu();
        assertNotNull(instance.getBantusButton());
        
        Button.ClickListener listener = (Button.ClickListener) instance.getBantusButton().getListeners(Button.ClickEvent.class).iterator().next();
        assertNotNull(listener);
        
        listener.buttonClick(new Button.ClickEvent(instance.getBantusButton()));
    }



}
