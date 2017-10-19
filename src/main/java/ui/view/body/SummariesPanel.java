/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.body;

import com.vaadin.ui.Component;
import factory.GraphEntityView;
import panel.FlexPanel;

/**
 *
 * @author zua
 */
public class SummariesPanel extends FlexPanel {

    private final FlexGridLayout overviews;

    public SummariesPanel(int columns) {
        overviews = new FlexGridLayout(columns);
        super.setSizeFull();
        super.setContent(overviews);
        super.setStyleName("items");
    }

    public void addItemView(Component component) {
        overviews.addItemView(component);
    }
}
