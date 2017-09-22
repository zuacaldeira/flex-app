/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author zua
 */
public class FlexPanel extends Panel {

    public FlexPanel() {
        super();
        configure();
    }

    public FlexPanel(String caption, Component content) {
        super(caption, content);
        configure();
    }

    private void configure() {
        setStyleName(ValoTheme.PANEL_BORDERLESS + " " + "flex-panel");
        setSizeFull();
    }
    
}
