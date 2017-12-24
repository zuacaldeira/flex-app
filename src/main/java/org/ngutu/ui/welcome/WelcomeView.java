/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import org.ngutu.ui.components.AbstractBody;
import org.ngutu.ui.components.AbstractMenu;
import org.ngutu.ui.components.AbstractView;

/**
 *
 * @author zua
 */
public class WelcomeView extends AbstractView {

    private static final long serialVersionUID = 8467619842785075810L;

    public WelcomeView() {
    }

    @Override
    protected AbstractMenu createMenu() {
        return new WelcomeMenu();
    }

    @Override
    protected AbstractBody createBody() {
        return new WelcomeBody();
    }

}
