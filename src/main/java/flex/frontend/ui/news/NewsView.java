/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news;

import flex.frontend.ui.news.article.ArticlesBody;
import flex.frontend.ui.FlexFooter;
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
        addComponents(getMenu(), getBody(), getFooter());
        this.setExpandRatio(getMenu(), .1f);
        this.setExpandRatio(getBody(), .9f);
    }
    
    private void initMenu() {
        NewsMenu menu = new NewsMenu();
        setMenu(menu);
    }

    private void initBody() {
        FlexBody body = new ArticlesBody();
        setBody(body);
    }

    private void initFooter() {
        FlexFooter footer = new FlexFooter();
        setFooter(footer);
    }

}
