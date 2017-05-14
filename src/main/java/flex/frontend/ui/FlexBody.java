/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import flex.frontend.ui.news.article.FlexPanel;

/**
 *
 * @author zua
 */
public abstract class FlexBody extends FlexPanel {
    
    private final HorizontalLayout layout;

    public FlexBody() {
        layout = new HorizontalLayout();
        layout.setSizeFull();
        layout.setSpacing(false);
        layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        super.setContent(layout);
        super.addStyleName("flex-body");
        
    }

    protected void addView(Component component) {
        layout.addComponent(component);
    }

    public HorizontalLayout getLayout() {
        return layout;
    }
}
