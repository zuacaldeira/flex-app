/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news;

import flex.frontend.ui.FlexBody;
import flex.frontend.ui.FlexFooter;
import flex.frontend.ui.FlexMenu;
import flex.frontend.ui.FlexMainView;

/**
 *
 * @author zua
 */
public class NewsView extends FlexMainView {

    public static final int MAX_ARTICLES = 100;
    
    public NewsView() {        
    }

    @Override
    protected FlexMenu createMenu() {
        return new NewsMenu();
    }

    @Override
    protected FlexBody createBody() {
        NewsBody myBody = new NewsBody();
        //myBody.addItems();
        return myBody;
    }

    @Override
    protected FlexFooter createFooter() {
        return new FlexFooter();
    }

    @Override
    public FlexFooter getFooter() {
        return super.getFooter(); 
    }

    @Override
    public NewsBody getBody() {
        return (NewsBody) super.getBody(); 
    }

    @Override
    public NewsMenu getMenu() {
        return (NewsMenu) super.getMenu();
    }
    
    

}
