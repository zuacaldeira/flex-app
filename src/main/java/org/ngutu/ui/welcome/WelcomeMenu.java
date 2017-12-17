/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import com.vaadin.ui.MenuBar;
import org.ngutu.ui.news.AbstractMenu;

/**
 *
 * @author zua
 */
public class WelcomeMenu extends AbstractMenu {

    private static final long serialVersionUID = 8366211712669711650L;

    public WelcomeMenu() {
        getSearchBox().setVisible(false);
    }

    @Override
    protected MenuBar createMenuBar() {
        return new WelcomeMenuBar(); 
    }

    @Override
    protected void search(String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void populate() {
    }
    
    
}
