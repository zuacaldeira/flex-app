/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.share;

import java.util.Properties;

/**
 *
 * @author zua
 */
public class FacebookProperties extends Properties {

    private static final long serialVersionUID = -5427752369139226274L;

    public FacebookProperties() {
        System.out.printf("(F_APP_ID, F_APP_SEC) = (%s, %s)\n", getAppId(), getAppSecret());
    }

    public String getAppId() {
        return System.getProperty("FACEBOOK_APP_ID");
    }

    public String getAppSecret() {
        return System.getProperty("FACEBOOK_APP_SECRET");
    }

}
