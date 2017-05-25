/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author zua
 */
public abstract class FlexMenu extends VerticalLayout {

    public FlexMenu() {
        super.setSizeUndefined();
        super.setMargin(false);
        super.setSpacing(false);
        super.setStyleName("flex-menu");
        super.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
    }
    
}
