/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Notification;
import ui.GraphEntityView;
import ui.news.article.SummariesLayout;

/**
 *
 * @author zua
 * @param <T>
 */
public class MasterDetailView<T extends GraphEntityView> extends HorizontalSplitPanel {
    private SummariesLayout<T> summaries;
    private BrowserFrame browserFrame;
    private T selected;
    private Object myData;

    public MasterDetailView() {
        initSummaries();
        initBrowserFrame();
        selected = null;
        myData = null;
        super.setStyleName("items");
        super.setFirstComponent(summaries);
        super.setSecondComponent(browserFrame);
        setSizeFull();
        setSplitPosition(25f);
    }

    public SummariesLayout<T> getSummaries() {
        return summaries;
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
    
    private void initSummaries() {
        summaries = new SummariesLayout<T>();
        summaries.setSizeFull();
    }
    
    private void initBrowserFrame() {
        browserFrame = new BrowserFrame();
        browserFrame.setSizeFull();
    }

    private void updateSelected(T itemView) {
        if(selected != null) {
            selected.minimize();
        }
        selected = itemView;
        selected.maximize();
        String url = selected.getItem().getUrl();
        browserFrame.setSource(new ExternalResource(url));
    }
    
    @Override
    public void addComponent(Component component) {
        summaries.addComponent(component);
        if(selected == null) {
            updateSelected((T) component);
        }
        ((T)component).addLayoutClickListener(event -> {
            updateSelected((T)component);
        });
    }
    
}
