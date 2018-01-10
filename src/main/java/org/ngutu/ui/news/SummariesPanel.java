/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.neovisionaries.i18n.CountryCode;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import factory.ArticleView;
import org.ngutu.ui.common.FlexPanel;

/**
 *
 * @author zua
 */
public class SummariesPanel extends FlexPanel {

    private static final long serialVersionUID = -1288952601019827111L;

    private HorizontalLayout base;
    private final int columns;
    private int currentColumn;

    public SummariesPanel(int columns) {
        this.columns = columns;
        this.currentColumn = 0;
        initBase();
        super.setContent(base);
        super.setSizeFull();
        super.setStyleName("items");
    }

    private void initBase() {
        base = new HorizontalLayout();
        base.setSizeFull();
        base.setHeightUndefined();
        for (int i = 0; i < columns; i++) {
            VerticalLayout holder = new VerticalLayout();
            holder.setWidth("100%");
            holder.setMargin(false);
            base.addComponent(holder);
        }
    }

    public void addItemView(Component component) {
        if (currentColumn == columns) {
            currentColumn = 0;
        }

        ((VerticalLayout) base.getComponent(currentColumn)).addComponent(component);
        currentColumn++;
    }

    void setVisibleIf(CountryCode code, boolean visible) {
        for (int i = 0; i < columns; i++) {
            VerticalLayout holder = (VerticalLayout) base.getComponent(i);
            for(int j = 0; j < holder.getComponentCount(); j++) {
                ArticleView articleView = (ArticleView) holder.getComponent(j);
                if(articleView.getArticle().getCountry().equalsIgnoreCase(code.getAlpha2())) {
                    articleView.setVisible(visible);
                }
            }
        }
    }

}
