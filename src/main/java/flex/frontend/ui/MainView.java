/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.ui.AbsoluteLayout;

/**
 *
 * @author zua
 */
public abstract class MainView extends AbsoluteLayout {
    
    private FlexMenu menu;
    private FlexBody body;
    private FlexFooter footer;

    public MainView() {
        initMenu();
        initBody();
        initFooter();
        setStyleName("main-view");
        setSizeFull();
        addComponent(footer, "bottom:0%");
        addComponent(body, "top:5%");
        addComponent(menu, "top:0%");
    }

    private void initMenu() {
        setMenu(createMenu());
        menu.setWidth("100%");
        menu.setHeight("1cm");
    }

    private void initBody() {
        setBody(createBody());
        body.setHeight("90%");
    }

    private void initFooter() {
        setFooter(createFooter());
        footer.setWidth("100%");
        footer.setHeight("1cm");
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

    protected abstract FlexMenu createMenu();
    protected abstract FlexBody createBody();
    protected abstract FlexFooter createFooter();
}
