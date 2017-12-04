/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import factory.GraphEntityView;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import db.FlexUser;
import factory.FlexViewFactory;
import components.FlexPanel;
import data.ArticlesRepository;
import data.DataProviderType;
import db.NewsArticle;
import factory.ArticleView;
import io.reactivex.Observable;

/**
 *
 * @author zua
 */
public class MasterDetailView extends FlexPanel {

    private static final long serialVersionUID = -2414042455007471125L;

    private MasterDetailThread worker;
    private final HorizontalLayout baseLayout;
    private SummariesPanel summariesPanel;
    private BrowserFrame infoFrame;
    private GraphEntityView selected;

    public MasterDetailView() {
        initSummaries(1);
        initBrowserFrame();
        baseLayout = new HorizontalLayout(summariesPanel, infoFrame);
        baseLayout.setSizeFull();
        baseLayout.setExpandRatio(summariesPanel, .25f);
        baseLayout.setExpandRatio(infoFrame, 1f);
        baseLayout.setSpacing(true);
        baseLayout.setMargin(true);
        super.setSizeFull();
        super.setContent(baseLayout);
    }

    private FlexUser getUser() {
        if (UI.getCurrent() != null) {
            return (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        }
        return null;
    }

    private void initSummaries(int c) {
        summariesPanel = new SummariesPanel(c);
        summariesPanel.setSizeFull();
    }

    private void initBrowserFrame() {
        infoFrame = new BrowserFrame();
        infoFrame.setSizeFull();
        infoFrame.setCaption("Reading...");
        infoFrame.setStyleName("info-frame");
    }

    public SummariesPanel getSummaries() {
        return summariesPanel;
    }

    public BrowserFrame getInfoFrame() {
        return infoFrame;
    }

    public GraphEntityView getSelected() {
        return selected;
    }

    private void updateSelected(GraphEntityView itemView) {
        if (selected != null) {
            selected.unselect();
        }
        selected = itemView;
        selected.select();

        String url = selected.getItem().getUrl();
        if (url != null) {
            infoFrame.setSource(new ExternalResource(url));
            infoFrame.setCaptionAsHtml(true);
            infoFrame.setCaption("<a href=\"" + url + "\"> Read this article outside Ngutu.org </a>");
        }
    }

    public void addSingleSummary(Component component) {
        getUI().access(() -> {
            summariesPanel.addItemView(component);
            if (selected == null) {
                updateSelected((GraphEntityView) component);
            }
            ((GraphEntityView) component).addLayoutClickListener(event -> {
                updateSelected((GraphEntityView) component);
            });
        });
    }

    public void full() {
        summariesPanel.full();
    }

    public void imagesOnly() {
        summariesPanel.imagesOnly();
    }

    public void titlesOnly() {
        summariesPanel.titlesOnly();
    }

    public final void refresh(DataProviderType type, String value) {
        if (worker != null) {
            worker.interrupt();
        }
        summariesPanel.getOverviews().removeAllComponents();
        worker = new MasterDetailThread(getUser(), type, value);
        worker.start();
    }

    private class MasterDetailThread extends Thread {

        private FlexUser user;
        private DataProviderType type;
        private String value;

        public MasterDetailThread(FlexUser user, DataProviderType type, String value) {
            this.user = user;
            this.type = type;
            this.value = value;
        }

        
        @Override
        public void run() {
            Observable<NewsArticle> observable = getNodes(this.type, this.value);
            observable.subscribe(next -> {
                ArticleView aView = FlexViewFactory.getInstance().createArticleView(this.user, next);
                addSingleSummary(aView);
            });
        }

        private Observable<NewsArticle> getNodes(DataProviderType type, String value) {
            Observable<NewsArticle> nodes = null;
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
}
