/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import data.ArticlesRepository;
import data.DataProviderType;
import db.auth.FlexUser;
import db.news.NewsArticle;
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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ix) {
        }
        Iterable<NewsArticle> nodes = getNodes(user, type, value);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ix) {
        }
        nodes.forEach(
                next -> {
                    masterDetailView.getUI().access(() -> {
                        ArticleView aView = FlexViewFactory.getInstance().createArticleView(user, next);
                        masterDetailView.addSingleSummary(aView);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ix) {
                        }
                    });
                });
    }

    private Iterable<NewsArticle> getNodes(FlexUser user, DataProviderType type, String value) {
        Iterable<NewsArticle> nodes = new LinkedList<>();
        if (user != null && value != null) {
            nodes = new ArticlesRepository().loadNodes(type, value, user);
        } else if (user != null && value == null) {
            nodes = new ArticlesRepository().loadNodes(type, user);
        } else if (user == null && value != null) {
            nodes = new ArticlesRepository().loadNodes(type, value);
        } else if (user == null && value == null) {
            nodes = new ArticlesRepository().loadNodes(type);
        }
        return nodes;
    }
}
