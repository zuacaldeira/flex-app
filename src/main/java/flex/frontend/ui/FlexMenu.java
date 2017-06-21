/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author zua
 */
public abstract class FlexMenu extends VerticalLayout {

    private FlexButton selected;
    private HorizontalLayout menu;
    
    public FlexMenu() {
        super.setSizeUndefined();
        super.setMargin(false);
        super.setSpacing(false);
        super.setStyleName("flex-menu");
        super.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        this.initMenu();
        this.addActions();
    }

    private void initMenu() {
        menu = new HorizontalLayout();
        menu.setSizeUndefined();
        super.addComponent(menu);
    }

    @Override
    public void addComponent(Component c) {
        menu.addComponent(c);
    }

    public FlexMainView getMainView() {
        return getMainView(getParent());
    }
    
    
    protected abstract void addActions();

    private FlexMainView getMainView(HasComponents parent) {
        if(parent == null) {
            return null;
        }
        if(parent instanceof FlexMainView) {
            return (FlexMainView) parent;
        }
        else {
            return getMainView(parent.getParent());
        }
    }
    
    public void setSelected(FlexButton button) {
        if(selected != null) {
            selected.unselect();
        }
        button.select();
        selected = button;
    }
    
}
