/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package button;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author zua
 */
public class DeleteButton extends FlexButton {

    public DeleteButton() {
        super(FlexCaptions.DELETE, VaadinIcons.TRASH);
        addStyleName(ValoTheme.BUTTON_DANGER);
    }

}
