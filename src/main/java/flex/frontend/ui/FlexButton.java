/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author zua
 */
public class FlexButton extends Button {
    
    public FlexButton(String caption, Resource icon) {
        super(caption, icon);
        setStyleName(ValoTheme.BUTTON_BORDERLESS);
    }
}
