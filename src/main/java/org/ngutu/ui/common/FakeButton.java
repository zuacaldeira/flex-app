/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.common;

import org.ngutu.ui.common.FlexActionButton;
import com.vaadin.icons.VaadinIcons;

/**
 *
 * @author zua
 */
public class FakeButton extends FlexActionButton {

    private static final long serialVersionUID = -4745498056327947341L;

    public FakeButton() {
        super(FlexCaptions.FAKE, VaadinIcons.EXCLAMATION_CIRCLE);
    }
    
}
