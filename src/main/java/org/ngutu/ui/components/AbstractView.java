/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.components;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.ngutu.ui.footer.FlexFooter;

/**
 *
 * @author zua
 */
public abstract class AbstractView extends VerticalLayout implements View {

    private static final long serialVersionUID = -4044534446064009709L;
    
    private AbstractMenu menu;
    private AbstractBody body;
    private FlexFooter footer;

    public AbstractView() {
    }
    
    private void initMenu() {
        menu = createMenu();
    }

    private void initBody() {
        body = createBody();
    }

    private void initFooter() {
        footer = new FlexFooter();
    }    

    protected abstract AbstractMenu createMenu();
    protected abstract AbstractBody createBody();

    public AbstractMenu getMenu() {
        return menu;
    }

    public AbstractBody getBody() {
        return body;
    }

    public FlexFooter getFooter() {
        return footer;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        View.super.enter(event); 
        initBody();
        initFooter();
        initMenu();
        super.addComponents(menu, body, footer);
        super.setExpandRatio(body, 1f);
        super.setStyleName("flex-view");
        super.setSizeFull();
        super.setMargin(false);
    }
    
    
    

    
}
