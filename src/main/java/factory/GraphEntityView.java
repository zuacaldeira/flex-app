/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import com.vaadin.ui.VerticalLayout;
import db.FlexUser;
import db.GraphEntity;
import ui.ui.SecuredUI;

/**
 *
 * @author zua
 * @param <T> A sub type of {@code GraphEntity}.
 */
public abstract class GraphEntityView<T extends GraphEntity> extends VerticalLayout {

    private final T item;
    private final FlexUser user;


    public GraphEntityView(FlexUser user, T entity) {
        this.user = user;
        this.item = entity;
        super.setSizeFull();
        super.setSpacing(true);
        super.setMargin(false);
        super.setStyleName("item");
    }

    public FlexUser getUser() {
        return user;
    }

    public T getItem() {
        return item;
    }

    @Override
    public SecuredUI getUI() {
        return (SecuredUI) super.getUI();
    }


    public void minimize() {
        setStyleName("item");
    }

    public void maximize() {
        setStyleName("item-selected");
    }
}
