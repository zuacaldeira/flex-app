/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import components.FlexButton;

/**
 *
 * @author zua
 */
public class FlexMenuButton extends FlexButton {

    private static final long serialVersionUID = -305217640090292509L;
    private NewsMenuBar newsMenuBar;

    public FlexMenuButton() {
        super(VaadinIcons.MENU);
        initialize();
    }

    public FlexMenuButton(String caption) {
        super(caption, VaadinIcons.MENU);
        initialize();
    }

    public FlexMenuButton(String caption, Resource icon) {
        super(caption, icon);
        initialize();
    }

    private void initialize() {
        //super.addStyleName(ValoTheme.BUTTON_QUIET);
        /*
        addClickListener(click -> {
            if (newsMenuBar == null) {
                newsMenuBar = new NewsMenuBar();
            }
            Window w = new Window(null, newsMenuBar);
            w.setSizeUndefined();
            w.setPosition(getPositionOfPercentile(50), 32);
            w.setDraggable(true);
            w.setResizable(false);
            w.setModal(true);
            w.addCloseListener(close -> {
                getUI().removeWindow(w);
                w.close();
            });
            getUI().addWindow(w);
        });
         */
    }

    private int getPositionOfPercentile(int percentile) {
        int pageWidth = Page.getCurrent().getBrowserWindowWidth();
        return (pageWidth * percentile) / 100;
    }
}
