/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.logo;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.ngutu.ui.viewproviders.FlexViews;

/**
 *
 * @author zua
 */
public class FlexLogo extends HorizontalLayout {

    private static final long serialVersionUID = 8041620231458973471L;

    private Label ngutu;
    
    public FlexLogo() {
        initLabel();
        super.addComponent(ngutu);
        super.setComponentAlignment(ngutu, Alignment.MIDDLE_CENTER);
        super.setSizeUndefined();
        super.addLayoutClickListener(event -> {
            UI.getCurrent().getNavigator().navigateTo(FlexViews.WELCOME);
        });
    }

    private void initLabel() {
        ngutu = new Label("ngutu.org");
        ngutu.setStyleName("flex-logo");
        ngutu.setSizeFull();
    }

}
