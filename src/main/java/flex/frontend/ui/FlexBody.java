/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author zua
 */
public abstract class FlexBody extends VerticalLayout {

    private GridLayout grid;
    
    public FlexBody() {
        grid = new GridLayout(4, 1);
        grid.setWidth("100%");  
        grid.setHeightUndefined();
        grid.setSpacing(true);
        addComponent(grid);
        setStyleName("flex-body");
    }

    public GridLayout getGrid() {
        return grid;
    }
    
    protected void addView(Component component) {
        grid.addComponent(component);
    }
    
}
