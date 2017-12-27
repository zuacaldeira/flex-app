/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import db.auth.FlexUser;
import db.news.NewsArticle;
import factory.ArticleView;
import factory.FlexViewFactory;

/**
 *
 * @author zua
 */
public class MasterDetailViewUpdaterThread extends Thread {

    private final MasterDetailView masterDetailView;
    private final Iterable<NewsArticle> nodes;
    private final FlexUser user;

    public MasterDetailViewUpdaterThread(FlexUser user, MasterDetailView masterDetailView, Iterable<NewsArticle> nodes) {
        this.user = user;
        this.masterDetailView = masterDetailView;
        this.nodes = nodes;
    }

    @Override
    public void run() {
        nodes.forEach(article -> {
            ArticleView aView = FlexViewFactory.getInstance().createArticleView(user, article);
            masterDetailView.addSingleSummary(aView);
        });
    }

}
