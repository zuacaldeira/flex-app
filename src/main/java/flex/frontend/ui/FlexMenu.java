/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author zua
 */
public abstract class FlexMenu extends VerticalLayout {

    private AbstractOrderedLayout topMenu;
    
    public FlexMenu() {
        super.setSizeUndefined();
        super.setMargin(false);
        super.setSpacing(false);
        super.setStyleName("flex-menu");
        super.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
        this.initTopMenu();
        this.addActions();
    }

    private void initTopMenu() {
        topMenu = new HorizontalLayout();
        topMenu.setSizeUndefined();
        super.addComponent(topMenu);
    }

    @Override
    public void addComponent(Component c) {
        topMenu.addComponent(c);
    }

    public AbstractOrderedLayout getTopMenu() {
        return topMenu;
    }
    
    
    
    protected abstract void addActions();
    
}
