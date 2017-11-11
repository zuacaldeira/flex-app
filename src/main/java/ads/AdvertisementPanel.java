/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import db.AmazonBook;
import factory.FlexViewFactory;
import java.util.Collection;
import panel.FlexPanel;
import services.FlexAmazonServiceInterface;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class AdvertisementPanel extends FlexPanel {

   private static final long serialVersionUID = -3339604655688381228L;
    
    private Image left;
    private GridLayout right;
    private HorizontalLayout base;

    public AdvertisementPanel() {
        base = new HorizontalLayout(left, right);
        base.setSizeFull();
        super.setContent(base);
        super.setSizeFull();
    }
    
    public void load(String person) {
        left = new Image("", new ExternalResource(getPhotoUrl(person)));
        right = new GridLayout(3,3);
        FlexAmazonServiceInterface service = ServiceLocator.getInstance().findAmazonService();
        Collection<AmazonBook> favoriteBooks = service.findFavoriteBooks(person);
        favoriteBooks.stream().forEach(book -> {
            right.addComponent(FlexViewFactory.getInstance().createAmazonBookView(book));
        });
    }

    private String getPhotoUrl(String person) {
        return ServiceLocator.getInstance().findPersonService().findPersonNamed(person).getPhotoUrl();
    }
}
