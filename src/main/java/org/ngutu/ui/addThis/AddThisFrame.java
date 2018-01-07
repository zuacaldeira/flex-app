/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.addThis;

import org.ngutu.ui.common.AdsFrame;
import com.vaadin.server.ThemeResource;

/**
 *
 * @author zua
 */
public class AddThisFrame extends AdsFrame {

    private static final long serialVersionUID = 7537932676487966201L;

    public AddThisFrame() {
        super(null, new ThemeResource("html/addThis.html"));
        setWidth("100%");
        setHeight("64px");
    }
    
}
