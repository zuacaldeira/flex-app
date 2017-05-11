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
    
    private GridLayout layout;

    public FlexBody() {
        this.setStyleName("flex-body");
        setSizeFull();
        layout = new GridLayout(4, 1);
        layout.setWidth("100%");
        layout.setHeightUndefined();
        layout.setSpacing(true);
        layout.setMargin(true);
        setContent(layout);

        addStyleName(ValoTheme.PANEL_BORDERLESS);
    }

    protected void addView(Component component) {
        layout.addComponent(component);
    }

    public GridLayout getLayout() {
        return layout;
    }
}
