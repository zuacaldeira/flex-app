/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.vaadin.ui.Component;
import view.FlexBody;
import view.FlexMainView;

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
        if(component == null) {
            return null;
        }
        
        if(component instanceof FlexBody) {
            return (FlexBody) component;
        }
        
        return getBody(component.getParent());        
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
