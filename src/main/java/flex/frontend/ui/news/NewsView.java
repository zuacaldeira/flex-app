/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news;

import com.vaadin.ui.VerticalLayout;
import flex.frontend.ui.FlexBody;
import flex.frontend.ui.FlexMenu;
import flex.frontend.ui.NewsMenu;

/**
 *
 * @author zua
 */
public class NewsView extends VerticalLayout {

    public static final int MAX_ARTICLES = 100;
    private FlexMenu menu;
    private FlexBody body;
    private FlexFooter footer;
    
    public NewsView() {        
        initMenu();
        initBody();
        initFooter();
        
        addComponents(menu, body);
        setExpandRatio(menu, .1f);
        setExpandRatio(body, .9f);
        setWidth("100%");
    }
    
    private void initMenu() {
        menu = new NewsMenu();
        menu.setSizeFull();
    }

    private void initBody() {
        body = new ArticlesInfoView();        
    }

    private void initFooter() {
        footer = new FlexFooter();
        footer.setHeight("2cm");
        footer.setWidth("100%");
        footer.setSizeFull();
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

    public void setBody(FlexBody flexBody) {
        replaceComponent(body, flexBody);
        body = flexBody;
    }
    
    
    
    
}
