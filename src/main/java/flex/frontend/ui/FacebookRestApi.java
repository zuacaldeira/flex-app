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
    private static final String MY_ACCESS_TOKEN = "EAAYOhasITgIBADuxCZBZAkB5SgRjSunDeuQA5lR9zZC8l2gdPpofDqr2C9ETqORUgeZAuLoHR8ESWMIa5qEYPRHuCuKnVhbGhB7A3ciujXAAvP9RnhkASRS2EiBcADBIz61sAJNijqUkmfXaRIY4gt8K7z6Xxykm9Nf0HZCMplXd6E2XZBJAJWBXZBoQQwZCSIJWhwHe5mOt3ydZC2KRQH2KBDMvGCdGdYkMZD";
    private static final String MY_APP_SECRET = "7349b4773f7086df12382c8618a909b7";
    
    private FacebookRestApi(){}
    
    public static String getAccessToken() {
        return MY_ACCESS_TOKEN;
    }
    
    public static String getAppSecret() {
        return MY_APP_SECRET;
    }

}
