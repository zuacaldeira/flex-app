/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.login;

import flex.frontend.ui.login.ApiInfo;
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
public class ApiInfoTest {
    
    public ApiInfoTest() {
    }

    @Test
    @UseDataProvider("apiInfosProvider")
    public void testApiInfo(ApiInfo apiInfo, String expectedName, String expectedApiKey, String expectedApiSecret, String expectedUrl) {
        assertNotNull(apiInfo);
        assertEquals(expectedApiKey, apiInfo.getApiKey());
        assertEquals(expectedApiSecret, apiInfo.getApiSecret());
        assertEquals(expectedName, apiInfo.getName());
        assertEquals(expectedUrl, apiInfo.getUrl());
    }
    
    @DataProvider
    public static Object[][] apiInfosProvider() {
        return new Object[][] {
            {ApiInfo.FACEBOOK_API, 
                "Facebook",
                "1704694336496974", // AppID
                "d8cc0ed762093fa24472ecba473d03b4", // App-Secret
                "https://graph.facebook.com/me"},
            {ApiInfo.FACEBOOK_DEV_API, 
                "Facebook",
                "1704817123151362", // AppID
                "7349b4773f7086df12382c8618a909b7", // App-Secret
                "https://graph.facebook.com/me"},
            {ApiInfo.FACEBOOK_ORIGINAL_API, 
                "Facebook",
                "170732353126405", // AppID
                "dd59293cda395bf38a88044c22937e7e", // App-Secret
                "https://graph.facebook.com/me"},
            {ApiInfo.GITHUB_API, 
                "GitHub",
                "97a7e251c538106e7922",
                "6a36b0992e5e2b00a38c44c21a6e0dc8ae01d83b",
                "https://api.github.com/user"},
            {ApiInfo.LINKEDIN_API, 
                "LinkedIn",
                "bp0aa1rxk2re",
                "Q2Na42cZmVs3OWnI",
                "https://api.linkedin.com/v1/people/~"},
            {ApiInfo.TWITTER_API,
                "Twitter",            
                "31ssXGMU4WW6KPxWwT6IMQ",
                "FR3wJmGyGAdpQMxB3vMreED2UnsHVb6nPF16f1RrtU",
                "https://api.twitter.com/1.1/account/settings.json"}
        };
    }
    
}
