/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.common;

import com.vaadin.server.Resource;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author zua
 */
public abstract class FlexActionButton extends FlexButton {

    private static final long serialVersionUID = 1703002983017827112L;

    public FlexActionButton(String caption, Resource icon) {
        super(icon);
        super.setDescription(caption);
        super.setStyleName(ValoTheme.BUTTON_BORDERLESS);
        super.setSizeUndefined();
    }
    
}
