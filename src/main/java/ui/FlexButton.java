/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;

/**
 *
 * @author zua
 */
public class FlexButton extends Button {

    public FlexButton() {
        initialize();
    }
    public FlexButton(String caption) {
        super(caption);
        initialize();
    }

    public FlexButton(Resource icon) {
        super(icon);
        initialize();
    }
    
    
    public FlexButton(String caption, Resource icon) {
        super(caption, icon);
        initialize();
    }
    
    private void initialize() {
        setSizeFull();
        setStyleName("flex-button");
    }

}
