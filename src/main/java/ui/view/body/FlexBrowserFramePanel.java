/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.body;

import com.vaadin.ui.BrowserFrame;
import panel.FlexPanel;

/**
 *
 * @author zua
 */
public class FlexBrowserFramePanel extends FlexPanel {

    private static final long serialVersionUID = 418236557923503815L;

    public FlexBrowserFramePanel(String caption, BrowserFrame component) {
        super(caption, component);
    }
    
    
}
