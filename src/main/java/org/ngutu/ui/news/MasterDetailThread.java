/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import data.DataProviderType;
import data.PublishedByRepository;
import db.auth.FlexUser;
import db.news.NewsArticle;
import db.relationships.PublishedBy;
import factory.ArticleView;
import factory.FlexViewFactory;
import java.util.LinkedList;

/**
 *
 * @author zua
 */
public class MasterDetailThread extends Thread {

    private final MasterDetailView masterDetailView;
    private final FlexUser user;
    private final DataProviderType type;
    private final String value;

    public MasterDetailThread(MasterDetailView view, FlexUser user, DataProviderType type, String value) {
        this.masterDetailView = view;
        this.user = user;
        this.type = type;
        this.value = value;
    }

    @Override
    public void run() {
        Iterable<PublishedBy> nodes = getNodes(user, type, value);
        if (nodes.iterator().hasNext()) {
            masterDetailView.getUI().access(() -> {
                masterDetailView.getSummaries().getOverviews().removeAllComponents();
            });
            nodes.forEach(next -> {
                masterDetailView.getUI().access(() -> {
                    NewsArticle article = next.getArticle();
                    ArticleView aView = FlexViewFactory.getInstance().createArticleView(user, next);
                    masterDetailView.addSingleSummary(aView);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ix) {
                    }
                });
            });
        }
    }

    private Iterable<PublishedBy> getNodes(FlexUser user, DataProviderType type, String value) {
        Iterable<PublishedBy> nodes = new LinkedList<>();
        if (user != null && value != null && !value.isEmpty()) {
            nodes = new PublishedByRepository().loadNodes(type, value, user);
        } else if (user != null && value == null) {
            nodes = new PublishedByRepository().loadNodes(type, user);
        } else if (user == null && value != null && !value.isEmpty()) {
            nodes = new PublishedByRepository().loadNodes(type, value);
        } else if (user == null && value == null) {
            nodes = new PublishedByRepository().loadNodes(type);
        }
        return nodes;
    }
}
