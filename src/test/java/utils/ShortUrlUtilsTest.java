/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class ShortUrlUtilsTest {
    
    public ShortUrlUtilsTest() {
    }

    /**
     * Test of getShortUrl method, of class ShortUrlUtils.
     */
    @Test
    @UseDataProvider("urls")
    public void testGetShortUrl(String shortUrl, String longUrl) {
        System.out.println("getShortUrl");
        assertEquals(shortUrl, new ShortUrlUtils().getShortUrl(longUrl));
    }
    
    @DataProvider
    public static Object[][] urls() {
        return new Object[][]{
            {"http://gestyy.com/welqs3", "https://vaadin.com/docs/v8/framework/advanced/advanced-embedding.html"}        
        };
    }
    
}
