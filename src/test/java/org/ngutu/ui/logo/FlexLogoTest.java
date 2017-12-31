/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.logo;

import db.auth.FlexUser;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.Test;

/**
 *
 * @author zua
 */
public class FlexLogoTest {

    private static final String TEST_USERNAME = "test:username";
    private static final String TEST_PASSWORD = "test:password";

    public FlexLogoTest() {
    }

    @Test
    public void testSomeMethod() {
        FlexUser user = new FlexUser(TEST_USERNAME, TEST_PASSWORD);
        FlexLogo logo = new FlexLogo();
        assertNotNull(logo);
    }

}
