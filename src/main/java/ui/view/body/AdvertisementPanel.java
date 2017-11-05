/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.body;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import db.NewsArticle;
import panel.FlexPanel;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class AdvertisementPanel extends FlexPanel {

    private static final long serialVersionUID = -3339604655688381228L;
    private final BrowserFrame browserFrame;
    
    public AdvertisementPanel() {
        browserFrame = new BrowserFrame("Now on Amazon...");
        super.setContent(browserFrame);
        super.setSizeFull();
    }

    public void refreshItems(NewsArticle newsArticle) {
        String response = ServiceLocator.getInstance().findAmazonService().createSignedRequest("Books", "Java");
        if(response != null) {
            browserFrame.setSource(new ExternalResource(response));
        }
    }

}
