/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grid;

import com.vaadin.ui.Component;
import factory.GraphEntityView;
import panel.FlexPanel;

/**
 *
 * @author zua
 * @param <T> A sub-type of {@code GraphEntityView}.
 */
public class SummariesLayoutPanel<T extends GraphEntityView> extends FlexPanel {

    private final FlexGridLayout<T> overviews;

    public SummariesLayoutPanel(int columns) {
        overviews = new FlexGridLayout<>(columns);
        super.setSizeFull();
        super.setContent(overviews);
        super.setStyleName("items");
    }

    public void addItemView(Component component) {
        overviews.addItemView(component);
    }
}
