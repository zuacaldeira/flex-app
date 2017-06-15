/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.crud;

import com.vaadin.event.LayoutEvents;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import flex.backend.news.db.GraphEntity;
import flex.backend.news.services.AbstractDBService;
import flex.frontend.ui.FlexBody;
import flex.frontend.ui.FlexViewFactory;
import flex.frontend.ui.GraphEntityView;
import flex.frontend.ui.news.article.FlexPanel;

/**
 *
 * @author zua
 * @param <T> Any subtype of GraphEntity.
 */
public abstract class CrudBody<T extends GraphEntity> extends FlexBody {
    
    private HorizontalSplitPanel splitPanel;
    private Panel summaries;
    private BrowserFrame browserFrame;
    private GraphEntityView selected;
    private Object myData;

    public CrudBody() {
    }
    
    @Override
    public void attach() {
        initSummaries();
        initBrowserFrame();
        initSplitPanel();
        setContent(splitPanel);
        setStyleName("items");
    }
    
    private void initSummaries() {
        VerticalLayout panelContent = new VerticalLayout();
        panelContent.setSpacing(true);
        panelContent.setMargin(false);

        summaries = new FlexPanel(null, panelContent);
        summaries.setStyleName(ValoTheme.PANEL_BORDERLESS + " " + "summaries");

        Iterable<T> items = loadItems();
        
        items.forEach(item -> {
            GraphEntityView itemView = FlexViewFactory.getInstance().createView(item);
            itemView.minimize();
            itemView.addLayoutClickListener((LayoutEvents.LayoutClickEvent event) -> {
            if(item.hasUrl() && item.getUrl() != null) {
                    browserFrame.setSource(new ExternalResource(item.getUrl()));
                    updateSelected(itemView);
                }
            });
            // First initialization
            if(selected == null) {
                updateSelected(itemView);
            }
            panelContent.addComponents(itemView);
        });
    }

    private void initBrowserFrame() {
        browserFrame = new BrowserFrame();
        browserFrame.setSizeFull();
        if(selected != null && selected.getItem().getUrl() != null) {
            browserFrame.setSource(new ExternalResource(selected.getItem().getUrl()));
        }
    }

    private void updateSelected(GraphEntityView itemView) {
        if(selected != null) {
            selected.minimize();
        }
        selected = itemView;
        selected.maximize();
    }

    
    private void initSplitPanel() {
        splitPanel = new HorizontalSplitPanel(summaries, browserFrame);
        splitPanel.setSizeFull();
        splitPanel.setSplitPosition(25f);
    }

    public BrowserFrame getBrowserFrame() {
        return browserFrame;
    }

    public HorizontalSplitPanel getSplitPanel() {
        return splitPanel;
    }

    public AbstractOrderedLayout getSummaries() {
        return (AbstractOrderedLayout) summaries.getContent();
    }

    public GraphEntityView getSelected() {
        return selected;
    }

    
    public Iterable<T> loadItems() {
        return getService().findAll();
    }
    
    protected abstract AbstractDBService<T> getService();
}
