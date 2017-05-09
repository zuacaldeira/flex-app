/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news;

import com.vaadin.server.VaadinRequest;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author zua
 */
public class NewsUITest extends UITest {
    
    public NewsUITest() {
    }

    /**
     * Test of init method, of class NewsUI.
     */
    @Test
    public void testInit() {
        System.out.println("init");

        VaadinRequest request = Mockito.mock(VaadinRequest.class);
        NewsUI instance = new NewsUI();
        assertNull(instance.getNewsView());
        instance.init(request);
        assertNotNull(instance.getNewsView());
    }

    /**
     * Test of getNewsView method, of class NewsUI.
     */
    @Test
    public void testGetNewsView() {
        System.out.println("getNewsView");
        
        VaadinRequest request = Mockito.mock(VaadinRequest.class);
        NewsUI instance = new NewsUI();
        assertNull(instance.getNewsView());
        instance.init(request);
        assertNotNull(instance.getNewsView());
    }
    
}
