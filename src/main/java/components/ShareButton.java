/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author zua
 */
public class ShareButton extends FlexButton {

    private static final long serialVersionUID = -5964953057780043865L;

    public ShareButton() {
        super(VaadinIcons.SHARE);
        super.setDescription("Share");
        super.setSizeUndefined();
        super.addStyleName(ValoTheme.BUTTON_QUIET);
    }

}
