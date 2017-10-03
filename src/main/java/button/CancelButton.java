/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package button;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.themes.ValoTheme;

/**
 * The Cancel Button is a user interaction element that stops the current user
 * interaction.
 *
 * @author zua
 */
public class CancelButton extends FlexButton {

    public CancelButton() {
        super("Cancel", VaadinIcons.CLOSE);
        addStyleName(ValoTheme.BUTTON_DANGER);
    }

}
