/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads;

import com.vaadin.server.Resource;
import com.vaadin.ui.BrowserFrame;

/**
 *
 * @author zua
 */
public abstract class AdsFrame extends BrowserFrame {

    private static final long serialVersionUID = -4280677617072735022L;

    public AdsFrame(String caption, Resource source) {
        super(caption, source);
        super.setSizeFull();
        super.setStyleName("ads-frame");
    }
    
}
