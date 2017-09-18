/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grid;

import factory.GraphEntityView;

/**
 *
 * @author zua
 * @param <T>
 */
public class SummariesLayout<T extends GraphEntityView> extends FlexGridLayout<T> {

    public SummariesLayout(int columns) {
        super(columns);
        super.setStyleName("summaries");
    }
}
