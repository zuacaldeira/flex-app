/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.menu;

import db.FlexUser;
import window.FlexWindow;

/**
 *
 * @author zua
 */
public class SearchWindow extends FlexWindow {

    public SearchWindow(FlexUser user) {
        super("Search News...", new NewsSearchForm());
    }
    
}
