/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.histories.videos;

import ui.FlexMainView;

/**
 *
 * @author zua
 */
public class LoadBantuVideos {

    private final int limit;
    private final FlexMainView view;

    public LoadBantuVideos(int limit, FlexMainView view) {
        this.limit = limit;
        this.view = view;
    }

    public int getLimit() {
        return limit;
    }

    public FlexMainView getView() {
        return view;
    }
    
    
    
}
