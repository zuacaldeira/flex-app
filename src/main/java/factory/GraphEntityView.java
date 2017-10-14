/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;
import db.FlexUser;
import db.GraphEntity;
import ui.SecuredUI;

/**
 *
 * @author zua
 * @param <T> A sub type of {@code GraphEntity}.
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

        VerticalLayout base = new VerticalLayout(infoHeader, infoBody, infoActions);
        base.setComponentAlignment(infoActions, Alignment.MIDDLE_RIGHT);
        this.addComponent(base);

        this.setSizeFull();
        this.setSpacing(true);
        this.setMargin(false);
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

    public FlexUser getUser() {
        return user;
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
    }

    public void maximize() {
        setStyleName("item-selected");
    }

    public abstract AbstractOrderedLayout createInfoHeader();

    public abstract AbstractOrderedLayout createInfoBody();

    public abstract AbstractOrderedLayout createInfoActions();

}
