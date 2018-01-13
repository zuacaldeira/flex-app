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
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 *
 * @author zua
 */
public class NewsBodyWorker extends Thread {

    private final MasterDetailView body;
    private final FlexUser user;
    private final DataProviderType type;
    private final String value;

    public NewsBodyWorker(MasterDetailView newsBody, FlexUser user, DataProviderType type, String value) {
        this.body = newsBody;
        this.user = user;
        this.type = type;
        this.value = value;
    }

    @Override
    public void run() {
        Observable<NewsArticle> observable = getNodes(user, type, value);
        try {
            Disposable disposable = observable.subscribe(
                    article -> {
                        
                        if (body != null && body.getUI() != null) {
                            body.getUI().access(() -> {
                                ArticleView aView = FlexViewFactory.getInstance().createArticleView(user, article);
                                body.addSingleSummary(aView);
                            });
                            //Thread.sleep(5000);
                        }
                    },
                    ex -> {
                        throw new RuntimeException("Error on subscribed data: " + ex.getMessage());
                    }
            );
            disposable.dispose();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private Observable<NewsArticle> getNodes(FlexUser user, DataProviderType type, String value) {
        if (user != null && value != null && !value.isEmpty()) {
            return new ArticlesRepository().loadNodes(type, value, user);
        } else if (user != null && value == null) {
            return new ArticlesRepository().loadNodes(type, user);
        } else if (user == null && value != null && !value.isEmpty()) {
            return new ArticlesRepository().loadNodes(type, value);
        } else {
            return new ArticlesRepository().loadNodes(type);
        }
    }

}
