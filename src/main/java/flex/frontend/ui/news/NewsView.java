/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news;

import flex.frontend.ui.FlexBody;
import flex.frontend.ui.MainView;

/**
 *
 * @author zua
 */
public class NewsView extends MainView {

    public static final int MAX_ARTICLES = 100;
    
    public NewsView() {        
        initMenu();
        initBody();
        initFooter();
        addComponents(getMenu(), getBody());
        setExpandRatio(getMenu(), .1f);
        setExpandRatio(getBody(), .9f);
        setWidth("100%");
    }
    
    private void initMenu() {
        NewsMenu menu = new NewsMenu();
        setMenu(menu);
    }

    private void initBody() {
        FlexBody body = new ArticlesInfoView();
        setBody(body);
    }

    private void initFooter() {
        FlexFooter footer = new FlexFooter();
        setFooter(footer);
    }

}
