/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.bantu;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import flex.frontend.ui.FlexButton;

/**
 *
 * @author zua
 */
public class HomeButton extends FlexButton {

    public HomeButton() {
        super(VaadinIcons.HOME);
        super.addClickListener(event -> {
            Page.getCurrent().setLocation("/flex-app");
        });
    }
    
}
