/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import flex.backend.news.db.GraphEntity;

/**
 *
 * @author zua
 * @param <T>
 */
public abstract class GraphEntityView<T extends GraphEntity> extends VerticalLayout {

    private final T graphEntity;
    
    private AbstractOrderedLayout infoHeader;
    private AbstractOrderedLayout infoBody;
    private AbstractOrderedLayout infoActions;

    public GraphEntityView(T entity) {
        this.graphEntity = entity;
        infoHeader = this.createInfoHeader();
        infoBody = this.createInfoBody();
        infoActions = this.createInfoActions();
        this.addComponents(infoHeader, infoBody, infoActions);
        this.setSizeUndefined();
        this.setSpacing(false);
        this.setMargin(new MarginInfo(false));
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
        FlexButton editButton = new EditButton();
        FlexButton deleteButton = new DeleteButton();
        FlexButton hideButton = new HideButton();

        HorizontalLayout actions = new HorizontalLayout(commentButton, editButton, hideButton, deleteButton);
        actions.setSizeUndefined();
        actions.setStyleName("controls");
        actions.setMargin(new MarginInfo(false, false, true, false));
        actions.setSpacing(false);
        return actions;
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

    
    
}
