/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.menu;

import com.vaadin.server.Page;
import db.FlexUser;
import window.FlexWindow;

/**
 *
 * @author zua
 */
public class SearchWindow extends FlexWindow {

    public SearchWindow(FlexUser user) {
        super(null, new NewsSearchForm());
        super.setModal(false);
        super.setPosition(Page.getCurrent().getBrowserWindowWidth()-getWindowWidthInPixels(), 0);
    }
    
    private int getWindowWidthInPixels() {
        float width = getWidth();
        Unit units = getWidthUnits();
        
        if(units == Unit.PERCENTAGE) {
            return (int) (width*Page.getCurrent().getBrowserWindowWidth()/100);
        }
        if(units == Unit.PIXELS) {
            return (int) width;
        }
        throw new RuntimeException("Cannot convert from " + units + " to pixels");
    }
    
}
