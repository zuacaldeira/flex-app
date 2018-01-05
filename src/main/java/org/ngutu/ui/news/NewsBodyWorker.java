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

    private final NewsBody body;
    private final FlexUser user;
    private final DataProviderType type;
    private final String value;

    public NewsBodyWorker(NewsBody newsBody, FlexUser user, DataProviderType type, String value) {
        this.body = newsBody;
        this.user = user;
        this.type = type;
        this.value = value;
    }

    @Override
    public void run() {
        Observable<NewsArticle> observable = getNodes(user, type, value);
        Disposable disposable = observable.subscribe(
                article -> {
                    if (body.getUI() != null) {
                        body.getUI().access(() -> {
                            ArticleView aView = FlexViewFactory.getInstance().createArticleView(user, article);
                            body.getMasterDetail().addSingleSummary(aView);
                        });
                        Thread.sleep(5000);
                    }
                    else {
                        return;
                    }
                },
                ex -> {
                    ex.printStackTrace();
                    return;
                }
        );
        disposable.dispose();
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
