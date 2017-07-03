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
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import db.news.FlexUser;
import db.news.GraphEntity;
import services.news.AbstractDBService;
import utils.FlexUtils;

/**
 *
 * @author zua
 * @param <T>
 */
public abstract class GraphEntityView<T extends GraphEntity> extends VerticalLayout implements ClickListener {

    private final T graphEntity;
    
    private AbstractOrderedLayout infoHeader;
    private AbstractOrderedLayout infoBody;
    private AbstractOrderedLayout infoActions;

    public GraphEntityView(T entity) {
        this.graphEntity = entity;
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
        favoriteButton.addClickListener(this);
        
        FlexUser user = getUser();
        if(user != null && user.hasFavorite(getItem())) {
            favoriteButton.addStyleName("yellow");
        }

        FlexButton fakeButton = new FakeButton();
        fakeButton.addClickListener(this);
        if(user != null && user.hasFake(getItem())) {
            fakeButton.addStyleName("red");
        }

        FlexButton hideButton = new HideButton();
        hideButton.addClickListener(this);
        if(user != null && user.hasFake(getItem())) {
            fakeButton.addStyleName("yellow");
        }

        FlexButton editButton = new EditButton();
        editButton.addClickListener(this);

        HorizontalLayout actions = new HorizontalLayout(commentButton, favoriteButton, fakeButton, hideButton, editButton);
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
    
    public FlexUser getUser() {
        if(getUI() != null) {
            return getUI().getFlexUser();
        }
        else if(getSession() != null) {
            return (FlexUser) getSession().getAttribute("user");
        }
        else {
            return null;
        }
    }
    
    public T getItem() {
        return graphEntity;
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
            Notification.show("Hide me!");
            getService().markAsRead(username, this.getItem());
            FlexUtils.getInstance().getBody(this).getContent().getSummaries().removeComponent(this);
        }
        else if(event.getButton() instanceof FavoriteButton) {
            Notification.show("Favorite");
            getService().markAsFavorite(username, this.getItem());
            event.getButton().addStyleName("yellow");
        }
        else if(event.getButton() instanceof FakeButton) {
            Notification.show("Fake");
            getService().markAsFake(username, this.getItem());
            event.getButton().addStyleName("red");
        }
    }

    public abstract AbstractDBService<T> getService();

    private String getUsername() {
        return getUI().getFlexUser().getUsername();
    }
}
