/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.body;

import com.vaadin.ui.Alignment;
import factory.GraphEntityView;
import factory.ArticleView;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author zua
 */
public class FlexGridLayout extends HorizontalLayout {

    private static final long serialVersionUID = 8947690159810342641L;

    private GraphEntityView selected;
    private int currentListIndex = 0;
    private final int columns;

    public FlexGridLayout(int c) {
        columns = c;
        for (int i = 0; i < c; i++) {
            VerticalLayout v = new VerticalLayout();
            v.setMargin(false);
            v.setSpacing(true);
            v.setSizeFull();
            v.setHeightUndefined();
            super.addComponent(v);
            super.setComponentAlignment(v, Alignment.TOP_CENTER);
        }
        super.setWidth("100%");
        super.setHeightUndefined();
        //super.setSpacing(true);
        //super.setMargin(false);
    }

    public void addItemView(Component component) {
        ((VerticalLayout) getComponent(currentListIndex)).addComponent(component);
        updateCurrentListIndex();
        ((GraphEntityView) component).addLayoutClickListener(event -> {
            updateSelected((GraphEntityView) component);
        });
        if (selected == null) {
            updateSelected((GraphEntityView) component);
        }
    }

    private void updateSelected(GraphEntityView itemView) {
        if (selected != null) {
            selected.minimize();
        }
        selected = itemView;
        selected.maximize();
    }

    private void updateCurrentListIndex() {
        if (currentListIndex >= getComponentCount() - 1) {
            currentListIndex = 0;
        } else {
            currentListIndex++;
        }
    }

    public int getColumns() {
        return columns;
    }

    public void full() {
        int i = getComponentCount();
        for(int j = 0; j < i; j++) {
            VerticalLayout l = (VerticalLayout) getComponent(j);
            int c = l.getComponentCount();
            for(int k = 0; k < c; k++) {
                ArticleView v = (ArticleView) l.getComponent(k);
                v.full();
            }
        }
    }

    public void imagesOnly() {
        int i = getComponentCount();
        for(int j = 0; j < i; j++) {
            VerticalLayout l = (VerticalLayout) getComponent(j);
            int c = l.getComponentCount();
            for(int k = 0; k < c; k++) {
                ArticleView v = (ArticleView) l.getComponent(k);
                v.imagesOnly();
            }
        }
    }

    public void titlesOnly() {
        int i = getComponentCount();
        for(int j = 0; j < i; j++) {
            VerticalLayout l = (VerticalLayout) getComponent(j);
            int c = l.getComponentCount();
            for(int k = 0; k < c; k++) {
                ArticleView v = (ArticleView) l.getComponent(k);
                v.titlesOnly();
            }
        }
    }
}
