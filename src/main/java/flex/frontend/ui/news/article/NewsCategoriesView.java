/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news.article;

import flex.frontend.ui.FlexBody;
import flex.frontend.ui.FlexFooter;
import flex.frontend.ui.FlexMenu;
import flex.frontend.ui.news.NewsView;

/**
 *
 * @author zua
 */
public class NewsCategoriesView extends NewsView {

    public NewsCategoriesView() {
    }

    @Override
    protected FlexMenu createMenu() {
        NewsCategoriesMenu menu = new NewsCategoriesMenu();
        return menu;
    }

    @Override
    protected FlexBody createBody() {
        NewsCategoriesBody myBody = new NewsCategoriesBody("general");
        return myBody;
    }

    @Override
    protected FlexFooter createFooter() {
        return new FlexFooter();
    }
}
