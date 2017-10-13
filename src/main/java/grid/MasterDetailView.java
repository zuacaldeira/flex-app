/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grid;

import factory.GraphEntityView;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import panel.FlexPanel;

/**
 *
 * @author zua
 * @param <T>
 */
public class MasterDetailView<T extends GraphEntityView> extends FlexPanel  {

    private FlexBrowserFramePanel browserFramePanel;
    private SummariesLayoutPanel<T> summariesPanel;
    private HorizontalLayout baseLayout;
    private T selected;
    private Object myData;
    private BrowserFrame browserFrame;

    public MasterDetailView() {
        selected = null;
        myData = null;
        initSummaries(1);
        initBrowserFrame();
        baseLayout = new HorizontalLayout(summariesPanel, browserFramePanel);
        baseLayout.setSizeFull();
        baseLayout.setExpandRatio(summariesPanel, .2f);
        baseLayout.setExpandRatio(browserFramePanel, .8f);
        super.setSizeFull();
        super.setContent(baseLayout);
    }
    
    private void initSummaries(int c) {
        summariesPanel = new SummariesLayoutPanel<T>(c);
        summariesPanel.setSizeFull();
    }
    
    private void initBrowserFrame() {
        browserFrame = new BrowserFrame();
        browserFrame.setSizeFull();
        browserFrame.setCaption("You are reading...");
        browserFramePanel = new FlexBrowserFramePanel(null, browserFrame);
    }


    public SummariesLayoutPanel<T> getSummaries() {
        return summariesPanel;
    }

    public BrowserFrame getBrowserFrame() {
        return browserFrame;
    }

    public T getSelected() {
        return selected;
    }

    public Object getMyData() {
        return myData;
    }

    private void updateSelected(T itemView) {
        if(selected != null) {
            selected.minimize();
        }
        selected = itemView;
        selected.maximize();
        String url = selected.getItem().getUrl();
        if(url != null) {
            browserFrame.setSource(new ExternalResource(url));
        }
    }
    
    public void addComponent(Component component) {
        summariesPanel.addItemView(component);
        if(selected == null) {
            updateSelected((T) component);
        }
        ((T)component).addLayoutClickListener(event -> {
            updateSelected((T)component);
        });
    }

}
