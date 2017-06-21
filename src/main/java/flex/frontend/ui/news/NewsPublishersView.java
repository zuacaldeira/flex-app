/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news;

import flex.frontend.ui.FlexBody;
import flex.frontend.ui.FlexFooter;
import flex.frontend.ui.FlexMenu;

/**
 *
 * @author zua
 */
public class NewsPublishersView extends NewsView {

    public NewsPublishersView() {
    }

    @Override
    protected FlexMenu createMenu() {
        return new NewsPublishersMenu();
    }

    @Override
    protected FlexBody createBody() {
        return new NewsPublishersBody();
    }

    @Override
    protected FlexFooter createFooter() {
        return super.createFooter();
    }
    
}
