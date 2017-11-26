/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.vaadin.ui.Component;
import org.ngutu.ui.news.NewsBody;
import org.ngutu.ui.news.NewsView;

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
        NewsView mainView = getMainView(component);
        return mainView.getBody();
    }


    public NewsView getMainView(Component component) {
        if(component == null) {
            return null;
        }
        
        if(component instanceof NewsView) {
            return (NewsView) component;
        }
        
        return getMainView(component.getParent());        
    }
    
}
