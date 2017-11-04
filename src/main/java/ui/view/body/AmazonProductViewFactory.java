/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.body;

import com.ECS.client.jax.Item;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Link;

/**
 *
 * @author zua
 */
public class AmazonProductViewFactory {
    
    public static Component getView(Item item) {
        return new Link("Visit", new ExternalResource(item.getDetailPageURL()));
    }

    private static String getImageUrl(Item item) {
        if(item.getSmallImage() != null && item.getSmallImage() != null) {
            return item.getSmallImage().getURL();
        }
        else if(item.getMediumImage()!= null && item.getMediumImage()!= null) {
            return item.getMediumImage().getURL();
        }
        else if(item.getLargeImage()!= null && item.getLargeImage()!= null) {
            return item.getLargeImage().getURL();
        }
        return null;
    }
    
    private static String getOffers(Item item) {
        return "Offers Draft";
    }
}
