/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.vaadin.ui.Component;
import ui.view.body.FlexBody;
import ui.view.main.FlexMainView;

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

    public FlexBody getBody(Component component) {
        FlexMainView mainView = getMainView(component);
        return mainView.getBody();
    }


    public FlexMainView getMainView(Component component) {
        if(component == null) {
            return null;
        }
        
        if(component instanceof FlexMainView) {
            return (FlexMainView) component;
        }
        
        return getMainView(component.getParent());        
    }
    
}
