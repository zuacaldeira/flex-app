/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author zua
 */
public abstract class FlexBody extends Panel {
    
    private GridLayout base;

    public FlexBody() {
        this.setStyleName("flex-body");
        setSizeFull();
        base = new GridLayout(4, 1);
        base.setWidth("100%");
        base.setHeightUndefined();
        base.setSpacing(true);
        setContent(base);
        addStyleName(ValoTheme.PANEL_BORDERLESS);
    }

    protected void addView(Component component) {
        base.addComponent(component);
    }
    
}
