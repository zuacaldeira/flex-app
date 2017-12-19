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
import com.vaadin.ui.UI;
import components.FlexPanel;
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
public class MasterDetailView extends FlexPanel {

    private static final long serialVersionUID = -2414042455007471125L;

    private final HorizontalLayout baseLayout;
    private SummariesPanel summariesPanel;
    private BrowserFrame infoFrame;
    private ArticleView selected;

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

    private void updateSelected(ArticleView itemView) {
        if (selected != null) {
            selected.unselect();
        }
        selected = itemView;
        selected.select();

        String url = selected.getArticle().getUrl();
        if (url != null) {
            infoFrame.setSource(new ExternalResource(url));
            infoFrame.setCaptionAsHtml(true);
            infoFrame.setCaption("<a href=\"" + url + "\" target=\"_blank\"> Read this article outside Ngutu.org </a>");
        }
    }

    public void addSingleSummary(Component component) {
        getUI().access(() -> {
            summariesPanel.addItemView(component);
            if (selected == null) {
                updateSelected((ArticleView) component);
            }
            ((GraphEntityView) component).addLayoutClickListener(event -> {
                updateSelected((ArticleView) component);
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
        FlexUser user = getUser();
        Iterable<PublishedBy> nodes = getNodes(user, type, value);
        updateMasterDetailView(user, nodes);
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

    private void updateMasterDetailView(FlexUser user, Iterable<PublishedBy> nodes) {
        summariesPanel.getOverviews().removeAllComponents();
        nodes.forEach(next -> {
            NewsArticle article = next.getArticle();
            ArticleView aView = FlexViewFactory.getInstance().createArticleView(user, next);
            UI.getCurrent().access(() -> {
                addSingleSummary(aView);
            });
        });
    }
}
