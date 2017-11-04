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
import db.Advertises;
import db.FlexUser;
import java.util.Collection;
import db.NewsArticle;
import panel.FlexPanel;
import services.FlexAdvertisementServiceInterface;
import utils.ServiceLocator;

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
    private AdvertisementPanel advertisementPanel;

    public MasterDetailView(FlexUser user) {
        this.user = user;
        selected = null;
        myData = null;
        initSummaries(1);
        initBrowserFrame();
        initAdvertisementPanel();
        baseLayout = new HorizontalLayout(summariesPanel, browserFramePanel, advertisementPanel);
        baseLayout.setSizeFull();
        baseLayout.setExpandRatio(summariesPanel, .2f);
        baseLayout.setExpandRatio(browserFramePanel, 1f);
        baseLayout.setExpandRatio(advertisementPanel, .2f);
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
    
    private void initAdvertisementPanel() {
        advertisementPanel = new AdvertisementPanel();
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
            advertisementPanel.refreshItems((NewsArticle)selected.getItem());
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
    
    public String getAdvertisementUrl(NewsArticle article) {
        FlexAdvertisementServiceInterface service = ServiceLocator.getInstance().findAdvertisementService();
        Collection<Advertises> all = service.findAll(article);
        if (!all.isEmpty()) {
            return all.iterator().next().getAdvertisementUrl();
        }
        return article.getUrl();
    }

}
