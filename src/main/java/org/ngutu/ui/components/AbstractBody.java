/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import components.FlexPanel;
import data.DataProviderType;
import db.auth.FlexUser;

/**
 *
 * @author zua
 */
public abstract class AbstractBody extends FlexPanel{

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
            System.out.println("Found USER -> " + UI.getCurrent().getSession().getAttribute("user"));
            return (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        }
        return null;
    }
    
    public abstract void populate(DataProviderType type, String value);
    
}
