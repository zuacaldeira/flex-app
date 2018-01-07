/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.adsense;

import org.ngutu.ui.common.AdsFrame;
import com.vaadin.server.ThemeResource;

/**
 *
 * @author zua
 */
public class AdSenseFrame extends AdsFrame {

    private static final long serialVersionUID = 1911606664709857426L;

    public AdSenseFrame() {
        super("Powered by AdSense", new ThemeResource("html/adsense.html"));
        setHeightUndefined();
    }
    
}
