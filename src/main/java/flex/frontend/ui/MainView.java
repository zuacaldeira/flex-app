/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.ui.VerticalLayout;
import flex.frontend.ui.news.FlexFooter;

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
        replaceComponent(body, flexBody);
        body = flexBody;
    }

    public void setMenu(FlexMenu menu) {
        replaceComponent(this.menu, menu);
        this.menu = menu;
    }
    
    
    public void setFooter(FlexFooter footer) {
        replaceComponent(this.footer, footer);
        this.footer = footer;
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
