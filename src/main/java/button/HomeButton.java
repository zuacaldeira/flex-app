/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package button;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;

/**
 *
 * @author zua
 */
public class HomeButton extends FlexButton {

    public HomeButton() {
        super(FlexCaptions.HOME, VaadinIcons.HOME);
        super.addClickListener(event -> {
            if(Page.getCurrent() != null) {
                Page.getCurrent().setLocation("/flex-app");
            }
        });
    }

}
