/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author zua
 */
public abstract class MainView extends VerticalLayout {
    
    private FlexMenu menu;
    private FlexBody body;
    private FlexFooter footer;

    public MainView() {
        setStyleName("main-view");
        setSizeFull();
    }

    public void setBody(FlexBody flexBody) {
        body = flexBody;
    }

    public void setMenu(FlexMenu menu) {
        this.menu = menu;
    }
    
    
    public void setFooter(FlexFooter footer) {
        this.footer = footer;
    }

    public void replaceBody(FlexBody flexBody) {
        replaceComponent(body, flexBody);
        setBody(flexBody);
    }

    public void replaceMenu(FlexMenu menu) {
        replaceComponent(this.menu, menu);
        setMenu(menu);
    }
    
    
    public void replaceFooter(FlexFooter footer) {
        replaceComponent(this.footer, footer);
        setFooter(footer);
    }

    public FlexMenu getMenu() {
        return menu;
    }

    public FlexBody getBody() {
        return body;
    }

    public FlexFooter getFooter() {
        return footer;
    }


    
}
