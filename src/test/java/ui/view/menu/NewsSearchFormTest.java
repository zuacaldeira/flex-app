/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.menu;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class NewsSearchFormTest {

    public NewsSearchFormTest() {
    }

    /**
     * Test of getSearchBox method, of class NewsSearchForm.
     */
    @Test
    public void testGetSearchBox() {
        System.out.println("getSearchBox");
        NewsSearchForm instance = new NewsSearchForm();
        assertNotNull(instance.getSearchBox());
    }

}
