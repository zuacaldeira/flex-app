/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.common;

import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import db.auth.FlexUser;
import factory.GraphEntityView;

/**
 *
 * @author zua
 */
public abstract class AbstractBody extends FlexPanel {

    private static final long serialVersionUID = 2130624780312088252L;

    public AbstractBody() {
        this.initBodyContent();
        super.addStyleName("flex-body");
        super.setSizeFull();
    }

    private void initBodyContent() {
        setContent(createBodyContent());
    }

    protected abstract Component createBodyContent();

    public FlexUser getUser() {
        if (UI.getCurrent() != null) {
            return (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        }
        return null;
    }

    public abstract void addSingleSummary(GraphEntityView component);
}
