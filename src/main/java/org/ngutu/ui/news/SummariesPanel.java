/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import db.FlexUser;
import factory.ArticleView;
import components.FlexPanel;

/**
 *
 * @author zua
 */
public class SummariesPanel extends FlexPanel {

    private static final long serialVersionUID = -1288952601019827111L;

    private final VerticalLayout overviews;
    private FlexUser user;

    public SummariesPanel(int columns) {
        initUser();
        overviews = new VerticalLayout();
        overviews.setMargin(false);
        super.setSizeFull();
        super.setContent(overviews);
        super.setStyleName("items");
    }

    private void initUser() {
        if (UI.getCurrent() != null) {
            System.out.println("Found USER -> " + UI.getCurrent().getSession().getAttribute("user"));
            user = (FlexUser) UI.getCurrent().getSession().getAttribute("user");
            System.out.println("NEWS VIEW USER -> " + user);
        }
    }

    public void addItemView(Component component) {
        overviews.addComponent(component);
    }

    public void full() {
        int c = overviews.getComponentCount();
        for (int k = 0; k < c; k++) {
            ArticleView v = (ArticleView) overviews.getComponent(k);
            v.full();
        }
    }

    public void imagesOnly() {
        int c = overviews.getComponentCount();
        for (int k = 0; k < c; k++) {
            ArticleView v = (ArticleView) overviews.getComponent(k);
            v.imagesOnly();
        }
    }

    public void titlesOnly() {
        int c = overviews.getComponentCount();
        for (int k = 0; k < c; k++) {
            ArticleView v = (ArticleView) overviews.getComponent(k);
            v.titlesOnly();
        }
    }

    public VerticalLayout getOverviews() {
        return overviews;
    }


}
