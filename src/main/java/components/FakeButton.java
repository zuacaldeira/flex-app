/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import org.ngutu.ui.components.FlexActionButton;
import com.vaadin.icons.VaadinIcons;

/**
 *
 * @author zua
 */
public class FakeButton extends FlexActionButton {

    public FakeButton() {
        super(FlexCaptions.FAKE, VaadinIcons.EXCLAMATION_CIRCLE);
    }
    
}
