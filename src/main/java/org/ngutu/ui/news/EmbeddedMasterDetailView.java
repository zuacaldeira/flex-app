/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Component;

/**
 *
 * @author zua
 */
public class EmbeddedMasterDetailView extends MasterDetailView {

    private static final long serialVersionUID = -3510987930890933869L;

    public EmbeddedMasterDetailView() {
    }

    @Override
    protected Component createTargetView() {
        BrowserFrame infoFrame = new BrowserFrame();
        infoFrame.setSizeFull();
        infoFrame.setCaption("Reading...");
        infoFrame.setStyleName("info-frame");
        return infoFrame;
    }

    @Override
    protected void updateTarget(String url) {
        if (url != null) {
            BrowserFrame infoFrame = getDetail();
            infoFrame.setSource(new ExternalResource(url));
            infoFrame.setCaptionAsHtml(true);
            infoFrame.setCaption("<a href=\"" + url + "\" target=\"_blank\"> Read this article outside Ngutu.org </a>");
        }
    }
    
    @Override
    public BrowserFrame getDetail() {
        return (BrowserFrame) super.getDetail();
    }

    @Override
    protected SummariesPanel createSummariesPanel() {
        return new SummariesPanel(1);
    }
    
}
