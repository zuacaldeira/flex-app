/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news;

import flex.frontend.ui.FlexBody;
import flex.frontend.ui.FlexFooter;
import flex.frontend.ui.FlexMenu;
import flex.frontend.ui.FlexView;

/**
 *
 * @author zua
 */
public class NewsView extends FlexView {

    public static final int MAX_ARTICLES = 100;
    
    public NewsView() {        
    }

    @Override
    protected FlexMenu createMenu() {
        return new NewsMenu();
    }

    @Override
    protected FlexBody createBody() {
        return new NewsBody();
    }

    @Override
    protected FlexFooter createFooter() {
        return new FlexFooter();
    }

}
