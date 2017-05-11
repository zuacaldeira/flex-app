/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;

/**
 *
 * @author zua
 */
public abstract class FlexMenu extends HorizontalLayout {

    public FlexMenu() {
        setSizeFull();
        setSpacing(false);
        setStyleName("flex-menu");
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
    }
    
}
