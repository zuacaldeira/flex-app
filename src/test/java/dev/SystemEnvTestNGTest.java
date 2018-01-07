/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev;

import java.util.Map;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.Test;

/**
 *
 * @author zua
 */
public class SystemEnvTestNGTest {
    
    public SystemEnvTestNGTest() {
    }

    @Test
    public void getEnv() {
        System.out.println("Test getEnv()...");
        assertNotNull(System.getenv());
        assertFalse(System.getenv().isEmpty());
        printMap(System.getenv());
    }
    
    @Test
    public void envComplete() {
        System.out.println("Test envComplete()...");
        assertNotNull(System.getenv("GRAPHENEDB_URL"));
        assertNotNull(System.getenv("FACEBOOK_APP_ID"));
        assertNotNull(System.getenv("FACEBOOK_APP_SECRET"));
        assertNotNull(System.getenv("FACEBOOK_APP_ACCESS_TOKEN"));
    }

    private void printMap(Map<String, String> map) {
        map.keySet().forEach(key -> {
            System.out.printf("(k, v) = (%s, %s)\n", key, map.get(key));
        });
    }
}
