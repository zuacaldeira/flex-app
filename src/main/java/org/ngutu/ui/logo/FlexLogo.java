/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.logo;

import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.ngutu.ui.news.FlexMenuButton;
import org.ngutu.ui.viewproviders.FlexViews;

/**
 *
 * @author zua
 */
public class FlexLogo extends FlexMenuButton {

    private static final long serialVersionUID = 8041620231458973471L;

    private Label ngutu;
    
    public FlexLogo() {
        super("ngutu.org");
        super.addClickListener(event -> {
            UI.getCurrent().getNavigator().navigateTo(FlexViews.WELCOME);
        });
        super.addStyleName("flex-logo");
    }

}
