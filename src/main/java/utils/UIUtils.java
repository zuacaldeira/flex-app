/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.vaadin.ui.Component;
import ui.view.body.NewsBody;
import ui.view.main.FlexNewsView;

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

    public NewsBody getBody(Component component) {
        FlexNewsView mainView = getMainView(component);
        return mainView.getBody();
    }


    public FlexNewsView getMainView(Component component) {
        if(component == null) {
            return null;
        }
        
        if(component instanceof FlexNewsView) {
            return (FlexNewsView) component;
        }
        
        return getMainView(component.getParent());        
    }
    
}
