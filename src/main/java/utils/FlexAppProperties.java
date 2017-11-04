/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Properties;

/**
 *
 * @author zua
 */
public class FlexAppProperties extends Properties {

    private static final long serialVersionUID = -2884093277007069167L;

    public FlexAppProperties(Properties defaults) {
        super(defaults);
    }
    
    public String getAccessKey() {
        return getProperty("amazon.accessKey");
    }
    
    public String getSecretKey() {
        return getProperty("amazon.secretKey");
    }
    
    public String getAssociateTag() {
        getProperty("amazon.associateTag");
    }
    
}
