/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.body;

import com.vaadin.ui.Component;
import panel.FlexPanel;

/**
 *
 * @author zua
 */
public class SummariesPanel extends FlexPanel {

    private static final long serialVersionUID = -1288952601019827111L;

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
    
    public void full() {
        overviews.full();
    }

    public void imagesOnly() {
        overviews.imagesOnly();
    }

    public void titlesOnly() {
        overviews.titlesOnly();
    }
}

