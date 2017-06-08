/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.bantu;

import flex.frontend.ui.FlexBody;
import flex.frontend.ui.FlexFooter;
import flex.frontend.ui.FlexMenu;
import flex.frontend.ui.FlexMainView;

/**
 *
 * @author zua
 */
public class BantuMainView extends FlexMainView {

    public BantuMainView() {
    }

    @Override
    protected FlexMenu createMenu() {
        return new BantuMenu();
    }

    @Override
    protected FlexBody createBody() {
        return new BantuBody();
    }

    @Override
    protected FlexFooter createFooter() {
        return new BantuFooter();
    }
    
    
    
}
