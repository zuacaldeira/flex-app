/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.footer;

import org.ngutu.ui.footer.FlexFooter;
import db.auth.FlexUser;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author zua
 */
public class FlexFooterTest {

    private static final String TEST_USERNAME = "test:username";
    private static final String TEST_PASSWORD = "test:password";

    public FlexFooterTest() {
    }

    @Test
    public void testSomeMethod() {
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexFooter footer = new FlexFooter();
        assertNull(footer.getUser());
    }

}
