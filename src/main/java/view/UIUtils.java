/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author zua
 */
public class UIUtils {
    
    private static UIUtils instance;
    
    private UIUtils() {
    }
    
    public static UIUtils getInstance() {
        if(instance == null) {
            instance = new UIUtils();
        }
        return instance;
    }

    public FlexBody getBody(FlexMenuBar menuBar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public FlexBody getBody(FlexMenu menu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public FlexMainView getMainView(FlexBody body) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
