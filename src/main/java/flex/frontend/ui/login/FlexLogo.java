/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.login;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Label;

/**
 *
 * @author zua
 */
public class FlexLogo extends Label {
    public FlexLogo() {
        super(VaadinIcons.MEGAFONE.getHtml(), ContentMode.HTML);
        this.setStyleName("flex-logo");
        this.setSizeUndefined();
    }
}
