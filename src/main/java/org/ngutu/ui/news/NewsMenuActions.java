/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import org.ngutu.ui.welcome.UserLayout;

/**
 *
 * @author zua
 */
public class NewsMenuActions extends MenuActions {

    private static final long serialVersionUID = -2319196537892647062L;
    private final SearchBox searchBox;

    public NewsMenuActions() {
        searchBox = new SearchBox();
        super.addComponents(
                searchBox,
                new UserLayout(),
                new NewsMenuBar()
        );
        super.setExpandRatio(searchBox, .5f);
        super.setSpacing(true);
        super.setWidth("100%");
        //super.setmargin()
    }

}
