/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.share;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author zua
 */
public class FacebookProperties extends Properties {

    private static final long serialVersionUID = -5427752369139226274L;

    public FacebookProperties() {
        try {
            super.load(new FileInputStream("src/main/resources/facebook.properties"));
        } catch (IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
    
    public String getAppId() {
        return getProperty("APP_ID");
    }
    
    public String getAppSecret() {
        return getProperty("APP_SECRET");
    }
    
}
