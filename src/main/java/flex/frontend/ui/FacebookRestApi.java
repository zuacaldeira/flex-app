/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

/**
 *
 * @author zua
 */
public class FacebookRestApi {
    private static final String MY_ACCESS_TOKEN = "EAAYOhasITgIBAJtwTVASnmwPOU39edZCq0WRpi03gxZA5lt9ezbaZCOcLpYLT8pXW7It2oWKE8BKaPX3NBVaGuv32WHoG7UWYec3OwXvANuNxIZCqwytKMO7Ak3xKQ0wxvL6gS1qgUSIxOlxmXBOLiKMFuxe4bdYUz4oqAHaRSf2TxnCFao0G7mhuIfT62txrO6fT3EMsZBazT82oiGQ8hhUsNr6fEt8ZD";
    private static final String MY_APP_SECRET = "7349b4773f7086df12382c8618a909b7";
    
    private FacebookRestApi(){}
    
    public static String getAccessToken() {
        return MY_ACCESS_TOKEN;
    }
    
    public static String getAppSecret() {
        return MY_APP_SECRET;
    }

}
