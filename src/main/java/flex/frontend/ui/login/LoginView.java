/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.login;

import flex.frontend.ui.FlexBody;
import flex.frontend.ui.FlexFooter;
import flex.frontend.ui.FlexMenu;
import flex.frontend.ui.FlexView;

/**
 *
 * @author zua
 */
public class LoginView extends FlexView {

    public LoginView() {
    }

    @Override
    protected FlexMenu createMenu() {
        return new LoginMenu();
    }

    @Override
    protected FlexBody createBody() {
        return new LoginBody();
    }

    @Override
    protected FlexFooter createFooter() {
        return new FlexFooter();
    }
    
}
