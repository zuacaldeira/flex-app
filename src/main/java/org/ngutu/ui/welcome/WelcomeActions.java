/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import org.ngutu.ui.news.FlexMenuButton;
import org.ngutu.ui.news.MenuActions;

/**
 *
 * @author zua
 */
public class WelcomeActions extends MenuActions {

    private static final long serialVersionUID = 6572895716542194487L;

    public WelcomeActions() {
        super.addComponents(new FlexMenuButton("News"),
                new FlexMenuButton("Blog"),
                new FlexMenuButton("Contacts"));
    }

}
