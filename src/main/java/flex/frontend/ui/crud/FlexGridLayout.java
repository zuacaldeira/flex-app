/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.crud;

import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import flex.frontend.ui.GraphEntityView;

/**
 *
 * @author zua
 * @param <T>
 */
public class FlexGridLayout<T extends GraphEntityView> extends GridLayout {

    private T selected;

    public FlexGridLayout(int c, int r) {
        super(c, r);
        super.setSizeFull();
        super.setStyleName("summaries");
    }
    
    @Override
    public void addComponent(Component component) {
        super.addComponent(component);
        ((T)component).addLayoutClickListener(event -> {
            updateSelected((T)component);
        });
        if(selected == null) {
            selected = (T) component;
        }
    }

    private void updateSelected(T itemView) {
        if(selected != null) {
            selected.minimize();
        }
        selected = itemView;
        selected.maximize();
    }
    
}
