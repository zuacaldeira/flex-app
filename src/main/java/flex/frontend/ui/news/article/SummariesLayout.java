/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news.article;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import flex.frontend.ui.GraphEntityView;

/**
 *
 * @author zua
 * @param <T>
 */
public class SummariesLayout<T extends GraphEntityView> extends FlexPanel {

    private final VerticalLayout layout;

    public SummariesLayout() {
        layout = new VerticalLayout();
        layout.setHeightUndefined();
        layout.setWidth("100%");
        layout.setMargin(false);
        layout.setSpacing(true);
        super.setStyleName("summaries");
        super.setSizeFull();
        super.setDebugId(getClass().getCanonicalName());
        super.setContent(layout);
    }
    
    public void addComponent(Component c) {
        layout.addComponent(c);
    }

    @Override
    public int getComponentCount() {
        return layout.getComponentCount();
    }

    public Component getComponent(int index) throws IndexOutOfBoundsException {
        return layout.getComponent(index);
    }

    public void removeComponent(Component c) {
        layout.removeComponent(c);
    }

   
}
