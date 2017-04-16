/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utils;

import flex.backend.services.NewsLoaderService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author zua
 */
public class ServiceLocator {

    
    private static final String NEWS_LOADER_SERVICE = "java:global/flex-app/NewsLoaderService";
    
    private static Object findService(String name) {
        try {
            InitialContext ctx = new InitialContext();
            return ctx.lookup(name);
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

    
    public static NewsLoaderService findNewsLoaderService() {
        return (NewsLoaderService) findService(NEWS_LOADER_SERVICE);
    }


}
