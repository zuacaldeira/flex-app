/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.news.article;

import ui.FlexGridLayout;
import ui.GraphEntityView;

/**
 *
 * @author zua
 * @param <T>
 */
public class SummariesLayout<T extends GraphEntityView> extends FlexGridLayout<T> {

    public SummariesLayout() {
        super(2);
        super.setStyleName("summaries");
    }
}
