/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.logo;

import view.logo.FlexLogo;
import db.FlexUser;
import org.junit.Test;
import static org.junit.Assert.*;

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
