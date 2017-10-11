/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.menu;

import db.FlexUser;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class SearchWindowTest {

    public SearchWindowTest() {
    }

    @Test
    public void testNew() {
        FlexUser user = new FlexUser("test:username", "test:password");
        assertNotNull(new SearchWindow(user));
    }

}
