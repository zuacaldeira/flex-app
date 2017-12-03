/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import db.FlexUser;
import db.GraphEntity;
import ui.SecuredUI;

/**
 *
 * @author zua
 * @param <T> A sub type of {@code GraphEntity}.
 */
public abstract class GraphEntityView<T extends GraphEntity> extends VerticalLayout {

    private static final long serialVersionUID = 749607225883641715L;

    private final T item;
    private FlexUser user;


    public GraphEntityView(T entity) {
        initUser();
        this.item = entity;
        super.setSizeFull();
        super.setSpacing(true);
        super.setMargin(false);
        super.setStyleName("item");
    }

    private void initUser() {
        if (UI.getCurrent() != null) {
            System.out.println("Found USER -> " + UI.getCurrent().getSession().getAttribute("user"));
            user = (FlexUser) UI.getCurrent().getSession().getAttribute("user");
            System.out.println("NEWS VIEW USER -> " + user);
        }
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


    public void unselect() {
        setStyleName("item");
    }

    public void select() {
        setStyleName("item-selected");
    }
}
