/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.news;

import com.vaadin.server.VaadinRequest;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author zua
 */
public class FlexAppUITestIT extends UITest {
    
    public FlexAppUITestIT() {
    }

    /**
     * Test of init method, of class FlexAppUI.
     */
    @Test
    public void testInit() {
        System.out.println("init");

        VaadinRequest request = Mockito.mock(VaadinRequest.class);
        FlexAppUI instance = new FlexAppUI();
        assertNull(instance.getContent());
        //instance.init(request);
        //assertNotNull(instance.getNewsView());
    }

    /**
     * Test of getNewsView method, of class FlexAppUI.
     */
    @Test
    public void testGetNewsView() {
        System.out.println("getNewsView");
        
        VaadinRequest request = Mockito.mock(VaadinRequest.class);
        FlexAppUI instance = new FlexAppUI();
        assertNull(instance.getContent());
        instance.init(request);
        //assertNotNull(instance.getNewsView());
    }
    
    
    @Test
    public void testServlet() {
        FlexAppUI.NewsUIServlet servlet = new FlexAppUI.NewsUIServlet();
        assertNotNull(servlet);
    }
    
}
