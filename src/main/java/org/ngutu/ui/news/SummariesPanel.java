/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import db.FlexUser;
import db.NewsArticle;
import factory.ArticleView;
import factory.FlexViewFactory;
import components.FlexPanel;
import io.reactivex.Observable;

/**
 *
 * @author zua
 */
public class SummariesPanel extends FlexPanel {

    private static final long serialVersionUID = -1288952601019827111L;

    private final VerticalLayout overviews;
    private final FlexUser user;

    public SummariesPanel(FlexUser user, int columns) {
        this.user = user;
        overviews = new VerticalLayout();
        overviews.setMargin(false);
        super.setSizeFull();
        super.setContent(overviews);
        super.setStyleName("items");
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

    public void refresh(Observable<NewsArticle> observable) {
        overviews.removeAllComponents();
        observable.subscribe(next -> {
            getUI().access(() -> {
                overviews.addComponent(FlexViewFactory.getInstance().createArticleView(user, next));
            });
        });
    }
    
    

}
