/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.body;

import factory.GraphEntityView;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import db.FlexUser;
import db.NewsArticle;
import java.util.Collection;
import panel.FlexPanel;

/**
 *
 * @author zua
 */
public class MasterDetailView extends FlexPanel {

    private static final long serialVersionUID = -2414042455007471125L;

    private FlexBrowserFramePanel browserFramePanel;
    private SummariesPanel summariesPanel;
    private HorizontalLayout baseLayout;
    private GraphEntityView selected;
    private Object myData;
    private BrowserFrame browserFrame;
    private final FlexUser user;

    public MasterDetailView(FlexUser user) {
        this.user = user;
        selected = null;
        myData = null;
        initSummaries(1);
        initBrowserFrame();
        baseLayout = new HorizontalLayout(summariesPanel, browserFramePanel);
        baseLayout.setSizeFull();
        baseLayout.setExpandRatio(summariesPanel, .33f);
        baseLayout.setExpandRatio(browserFramePanel, 1f);
        baseLayout.setSpacing(true);
        baseLayout.setMargin(true);
        super.setSizeFull();
        super.setContent(baseLayout);
    }

    private void initSummaries(int c) {
        summariesPanel = new SummariesPanel(user, c);
        summariesPanel.setSizeFull();
    }

    private void initBrowserFrame() {
        browserFrame = new BrowserFrame();
        browserFrame.setSizeFull();
        browserFrame.setCaption("You are reading...");
        browserFramePanel = new FlexBrowserFramePanel(null, browserFrame);
    }

    public SummariesPanel getSummaries() {
        return summariesPanel;
    }

    public BrowserFrame getBrowserFrame() {
        return browserFrame;
    }

    public GraphEntityView getSelected() {
        return selected;
    }

    public Object getMyData() {
        return myData;
    }

    private void updateSelected(GraphEntityView itemView) {
        if (selected != null) {
            selected.unselect();
        }
        selected = itemView;
        selected.select();
        String url = selected.getItem().getUrl();
        if (url != null) {
            browserFrame.setSource(new ExternalResource(url));
        }
    }

    public void addComponent(Component component) {
        summariesPanel.addItemView(component);
        if (selected == null) {
            updateSelected((GraphEntityView) component);
        }
        ((GraphEntityView) component).addLayoutClickListener(event -> {
            updateSelected((GraphEntityView) component);
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

    public void update(Collection<NewsArticle> nodes) {
        summariesPanel.update(nodes);
    }
}
