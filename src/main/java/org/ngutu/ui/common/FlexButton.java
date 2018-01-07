/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.common;

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author zua
 */
public class FlexButton extends Button {

    private static final long serialVersionUID = 2790652223146465994L;

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
        setStyleName(ValoTheme.BUTTON_FRIENDLY);
        setHeightUndefined();
    }

}
