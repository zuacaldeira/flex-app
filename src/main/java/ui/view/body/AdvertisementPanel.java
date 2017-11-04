/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.body;

import com.ECS.client.jax.Items;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import db.NewsArticle;
import de.malkusch.amazon.ecs.exception.RequestException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import panel.FlexPanel;
import utils.AmazonApi;

/**
 *
 * @author zua
 */
public class AdvertisementPanel extends FlexPanel {

    private static final long serialVersionUID = -3339604655688381228L;
    private final VerticalLayout advertisementList;

    public AdvertisementPanel() {
        advertisementList = new VerticalLayout();
        super.setContent(advertisementList);
        super.setSizeFull();
    }

    public void refreshItems(NewsArticle newsArticle) {
        try {
            Items items = new AmazonApi().makeApiCall("Books", "Java");
            items.getItem().forEach(product -> {
                advertisementList.addComponent(AmazonProductViewFactory.getView(product));
            });
            Notification.show("Found items " + items);
        } catch (RequestException | IOException ex) {
            Logger.getLogger(AdvertisementPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
