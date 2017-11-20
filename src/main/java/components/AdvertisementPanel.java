/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import com.vaadin.event.LayoutEvents;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import db.AmazonBook;
import db.Person;
import factory.AmazonBookView;
import factory.FlexViewFactory;
import java.util.Collection;
import services.FlexAmazonServiceInterface;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class AdvertisementPanel extends FlexPanel implements LayoutClickListener {

    private static final long serialVersionUID = -3339604655688381228L;

    private Component left;
    private GridLayout right;
    private HorizontalLayout base;
    private Person person;

    public AdvertisementPanel(String name) {
        load(name);
        base = new HorizontalLayout(left, right);
        base.setSizeFull();
        super.setContent(base);
        super.setSizeFull();
    }

    public void load(String name) {
        initLeft(name);
        initRight(name);
    }

    private Person getPersonName(String name) {
        return ServiceLocator.getInstance().findPersonService().findPersonNamed(name);
    }

    @Override
    public void layoutClick(LayoutEvents.LayoutClickEvent event) {
        Notification.show("Clicked on " + event.getClickedComponent().toString());
    }

    private void initLeft(String name) {
        person = getPersonName(name);
        if (person != null && person.getPhotoUrl() != null) {
            left = new Image("", new ExternalResource(person.getUrl()));
        } else {
            left = new Image("", VaadinIcons.USER_HEART);
        }
    }

    private void initRight(String name) {
        right = new GridLayout(3, 3);
        FlexAmazonServiceInterface service = ServiceLocator.getInstance().findAmazonService();
        Collection<AmazonBook> favoriteBooks = service.findFavoriteBooks(name);
        favoriteBooks.stream().forEach(book -> {
            AmazonBookView amazonBookView = FlexViewFactory.getInstance().createAmazonBookView(book);
            amazonBookView.addLayoutClickListener(this);
            right.addComponent(amazonBookView);
        });
    }

}
