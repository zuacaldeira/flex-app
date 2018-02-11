/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import org.ngutu.ui.common.AbstractView;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;
import data.ArticlesRepository;
import data.DataProviderType;
import db.auth.FlexUser;
import db.news.NewsArticle;
import org.ngutu.ui.common.AbstractBody;

/**
 *
 * @author zua
 */
public class NewsView extends AbstractView {

    private static final long serialVersionUID = 8467619842785075810L;

    public NewsView() {
    }

    public void replaceBody(AbstractBody flexBody) {
        replaceComponent(getBody(), flexBody);
    }

    private FlexUser getUser() {
        if (UI.getCurrent() != null) {
            return (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        }
        return null;
    }

    @Override
    protected NewsMenu createMenu() {
        return new NewsMenu();
    }

    @Override
    protected MasterDetailView createBody() {
        if (isMap()) {
            return new MapMasterDetailView();
        } else if (isExternal()) {
            return new ExternalMasterDetailView();
        } else {
            return new EmbeddedMasterDetailView();
        }
    }

    @Override
    public NewsBody getBody() {
        return (NewsBody) super.getBody();
    }

    @Override
    public NewsMenu getMenu() {
        return (NewsMenu) super.getMenu();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        MasterDetailView newBody = createBody();
        replaceBody(newBody);

        String parameters = event.getParameters();
        if (parameters == null || parameters.isEmpty()) {
            fillBodyWithNews(newBody, DataProviderType.LATEST, null);
        } else if (!parameters.isEmpty()) {
            parameters = parameters.replace('-', ' ');
            String parts[] = parameters.split("/");
            String context = null;
            String target = null;
            if (parts.length == 2) {
                System.out.println("2-PARTS " + parts[0] + " - " + parts[1]);
                context = parts[0];
                target = parts[1];
                fillBodyWithNews(newBody, DataProviderType.valueOf(context.toUpperCase()), target);
            } else if (parts.length == 1) {
                System.out.println("1-PART " + parts[0]);
                context = parts[0];
                fillBodyWithNews(newBody, DataProviderType.valueOf(context.toUpperCase()), null);
            }
        }
    }

    private boolean isExternal() {
        return "external".equals(UI.getCurrent().getSession().getAttribute("view"));
    }

    private boolean isMap() {
        return "map".equals(UI.getCurrent().getSession().getAttribute("view"));
    }

    
    /**
     * private void fillBodyWithNews(MasterDetailView body, DataProviderType type, String value) {
        Iterable<NewsArticle> observable = getNodes(getUser(), type, value);
        try {
            Disposable disposable = observable.subscribe(
                    article -> {
                        if (body != null && body.getUI() != null) {
                            ArticleView aView = FlexViewFactory.getInstance().createArticleView(getUser(), article);
                            body.addSingleSummary(aView);
                        }
                    },
                    ex -> {
                        throw new RuntimeException("Error on subscribed data: " + ex.getMessage());
                    }
            );
            disposable.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/
    private void fillBodyWithNews(MasterDetailView body, DataProviderType type, String value) {
        new NewsBodyWorker(body, getUser(), type, value).start();
    }

    private Iterable<NewsArticle> getNodes(FlexUser user, DataProviderType type, String value) {
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
