/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author zua
 * @param <T>
 */
public class FlexGridLayout<T extends GraphEntityView> extends HorizontalLayout {

    private T selected;
    private int currentListIndex = 0;

    public FlexGridLayout(int c) {
        for(int i = 0 ; i <  c; i++) {
            VerticalLayout v = new VerticalLayout();
            v.setMargin(false);
            v.setSpacing(false);
            super.addComponent(v);
        }
        super.setSizeUndefined();
        super.setHeightUndefined();
        super.setSpacing(false);
        super.setMargin(false);
    }
    
    public void addItemView(Component component) {
        ((VerticalLayout)getComponent(currentListIndex)).addComponent(component);
        updateCurrentListIndex();
        ((T)component).addLayoutClickListener(event -> {
            updateSelected((T)component);
        });
        if(selected == null) {
            updateSelected((T) component);
        }
    }

    private void updateSelected(T itemView) {
        if(selected != null) {
            selected.minimize();
        }
        selected = itemView;
        selected.maximize();
    }

    private void updateCurrentListIndex() {
        if(currentListIndex >= getComponentCount()-1) {
            currentListIndex = 0;
        } else {
            currentListIndex++;
        }
    }
    
}
