/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news.article;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author zua
 */
public abstract class SummariesLayout extends VerticalLayout {

    private final VerticalLayout layout;

    public SummariesLayout() {
        layout = new VerticalLayout();
        layout.setHeightUndefined();
        layout.setWidth("100%");
        layout.setMargin(false);
        layout.setSpacing(true);
        super.setStyleName("summaries");
        super.setMargin(false);
        super.setSizeFull();
        super.setDebugId(getClass().getCanonicalName());
        super.addComponent(layout);
    }
    
    @Override
    public void addComponent(Component c) {
        layout.addComponent(c);
    }

    @Override
    public int getComponentCount() {
        return layout.getComponentCount();
    }

    @Override
    public Component getComponent(int index) throws IndexOutOfBoundsException {
        return layout.getComponent(index);
    }

    @Override
    public void removeComponent(Component c) {
        layout.removeComponent(c);
        markAsDirty();
    }

   
}
