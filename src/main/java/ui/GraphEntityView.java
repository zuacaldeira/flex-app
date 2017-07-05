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
    
    public GraphEntityView(FlexUser user, T entity) {
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
        String username = getUsername();
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

    private String getUsername() {
        return user.getUsername();
    }


    public FlexUser getUser() {
        return user;
    }

    private void handleHideClick(HideButton button) {
        if(!button.getStyleName().contains("purple")) {
            getUI().access(() -> {
                button.addStyleName("green");
                button.setDescription("Mark as Read");
                getService().markAsRead(getUsername(), this.getItem());
            });
        }
        else {
            getUI().access(()->{
                button.removeStyleName("green");
                button.setDescription("Mark as Unread");
                getService().removeMarkAsRead(getUsername(), this.getItem());
            });
        }
    }

    private void handleFavouriteClick(FavoriteButton button) {
        if(!button.getStyleName().contains("yellow")) {
            button.addStyleName("yellow");
            button.setDescription("Unmark Favorite");
            getService().markAsFavorite(getUsername(), this.getItem());
        }
        else {
            button.removeStyleName("yellow");
            button.setDescription("Mark as Favorite");
            getService().removeMarkAsFavorite(getUsername(), this.getItem());
        }
    }

    private void handleFakeClick(FakeButton button) {
        if(!button.getStyleName().contains("red")) {
            button.addStyleName("red");
            button.setDescription("Unmark Fake");
            getService().markAsFake(getUsername(), this.getItem());
        }
        else {
            button.removeStyleName("red");
            button.setDescription("Mark as Fake");
            getService().removeMarkAsFake(getUsername(), this.getItem());
        }
    }
    
    
    
    
}
