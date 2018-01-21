/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import org.ngutu.ui.common.HomeButton;

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
                new HomeButton(),
                new FlexMenuButton("Latest"),
                new FlexMenuButton("Publishers"),
                new FlexMenuButton("Categories"),
                new FlexMenuButton("Languages"),
                new FlexMenuButton("Countries"),
                new FlexMenuButton("Views"),
                searchBox
        );
        //super.setExpandRatio(searchBox, .25f);
        super.setSizeUndefined();
    }

}
