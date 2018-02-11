/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.server.Resource;
import com.vaadin.ui.themes.ValoTheme;
import org.ngutu.ui.common.FlexButton;

/**
 *
 * @author zua
 */
public class FlexMenuButton extends FlexButton {

    private static final long serialVersionUID = -305217640090292509L;

    public FlexMenuButton() {
        initialize();
    }

    public FlexMenuButton(String caption) {
        super(caption);
        initialize();
    }

    public FlexMenuButton(String caption, Resource icon) {
        super(caption, icon);
        initialize();
    }

    private void initialize() {
        setSizeFull();
        setStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED + " menu-action");
    }
}
