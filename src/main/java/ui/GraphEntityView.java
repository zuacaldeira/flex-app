/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import db.news.FlexUser;
import db.news.GraphEntity;
import services.news.AbstractDBService;
import services.news.FlexUserService;
import utils.ServiceLocator;

/**
 *
 * @author zua
 * @param <T>
 */
public abstract class GraphEntityView<T extends GraphEntity> extends VerticalLayout implements ClickListener {

    private final T item;
    private FlexUser user;
    
    private AbstractOrderedLayout infoHeader;
    private AbstractOrderedLayout infoBody;
    private AbstractOrderedLayout infoActions;
    private final FlexUserService userService;
    
    public GraphEntityView(FlexUser user, T entity) {
        userService = ServiceLocator.getInstance().findUserService();
        this.user = user;
        this.item = entity;
        infoHeader = this.createInfoHeader();
        infoBody = this.createInfoBody();
        infoActions = this.createInfoActions();
        this.addComponents(new VerticalLayout(infoHeader, infoBody, infoActions));
        this.setSizeFull();
        this.setSpacing(false);
        this.setMargin(new MarginInfo(true));
        this.setStyleName("item");
    }

    public AbstractOrderedLayout getInfoHeader() {
        return infoHeader;
    }

    public AbstractOrderedLayout getInfoBody() {
        return infoBody;
    }

    public AbstractOrderedLayout getInfoActions() {
        return infoActions;
    }
    
    private String getUsername() {
        return user.getUsername();
    }


    public FlexUser getUser() {
        return user;
    }

    
    
    public abstract AbstractOrderedLayout createInfoHeader();
    public abstract AbstractOrderedLayout createInfoBody();
    
    public  AbstractOrderedLayout createInfoActions() {
        FlexButton commentButton = new CommentButton();
        commentButton.addClickListener(this);

        FlexButton favoriteButton = new FavoriteButton();
        FlexButton fakeButton = new FakeButton();
        FlexButton hideButton = new HideButton();

        hideButton.addClickListener(this);
        fakeButton.addClickListener(this);
        favoriteButton.addClickListener(this);
        
        if(user != null && getService().isFavorite(getUsername(), item)) {
            favoriteButton.addStyleName("yellow");
        }

        if(user != null && getService().isFake(getUsername(), item)) {
            fakeButton.addStyleName("red");
        }

        if(user != null && getService().isRead(getUsername(), item)) {
            hideButton.addStyleName("purple");
        }


        HorizontalLayout actions = new HorizontalLayout(commentButton, favoriteButton, fakeButton, hideButton);
        actions.setSizeUndefined();
        actions.setStyleName("controls");
        actions.setMargin(new MarginInfo(false, false, true, false));
        actions.setSpacing(false);
        return actions;
    }
    
    @Override
    public SecuredUI getUI() {
        return (SecuredUI) super.getUI();
    }
    
    public T getItem() {
        return item;
    }
    
    public void minimize() {
        setStyleName("item");
        infoActions.setVisible(false);
        infoBody.setVisible(false);
    }

    public void maximize() {
        setStyleName("item-selected");
        infoActions.setVisible(true);
        infoBody.setVisible(true);
    }

        @Override
    public void buttonClick(Button.ClickEvent event) {
        if(event.getButton() instanceof HideButton) {
            handleHideClick((HideButton) event.getButton());
        }
        else if(event.getButton() instanceof FavoriteButton) {
            handleFavouriteClick((FavoriteButton) event.getButton());            
        }
        
        else if(event.getButton() instanceof FakeButton) {
            handleFakeClick((FakeButton) event.getButton());
        }
    }

    public abstract AbstractDBService<T> getService();


    private void handleHideClick(HideButton button) {
        if(!button.getStyleName().contains("purple")) {
            getService().markAsRead(getUsername(), this.getItem());
            button.addStyleName("purple");
            button.setDescription("Mark as Read");
        }
        else {
            getService().removeMarkAsRead(getUsername(), this.getItem());
            button.removeStyleName("purple");
            button.setDescription("Mark as Unread");
        }        
        ((VerticalLayout)getParent()).removeComponent(this);
    }

    private void handleFavouriteClick(FavoriteButton button) {
        if(!button.getStyleName().contains("yellow")) {
            getService().markAsFavorite(getUsername(), this.getItem());
            button.addStyleName("yellow");
            button.setDescription("Unmark Favorite");
        }
        else {
            getService().removeMarkAsFavorite(getUsername(), this.getItem());
            button.removeStyleName("yellow");
            button.setDescription("Mark as Favorite");
        }
    }

    private void handleFakeClick(FakeButton button) {
        if(!button.getStyleName().contains("red")) {
            getService().markAsFake(getUsername(), this.getItem());
            button.addStyleName("red");
            button.setDescription("Unmark Fake");
        }
        else {
            getService().removeMarkAsFake(getUsername(), this.getItem());
            button.removeStyleName("red");
            button.setDescription("Mark as Fake");
        }
    }    
}
