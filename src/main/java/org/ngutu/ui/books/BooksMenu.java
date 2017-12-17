/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.books;

import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import db.auth.FlexUser;
import org.ngutu.ui.news.AbstractMenu;

/**
 *
 * @author zua
 */
public class BooksMenu extends AbstractMenu {

    private static final long serialVersionUID = 8366211712669711650L;


    public BooksMenu() {
        getSearchBox().setVisible(false);
    }

    @Override
    protected void search(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected MenuBar createMenuBar() {
        return new BooksMenuBar();
    }
    
    private FlexUser getUser() {
        if (UI.getCurrent() != null) {
            System.out.println("Found USER -> " + UI.getCurrent().getSession().getAttribute("user"));
            return (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        }
        return null;
    }

    @Override
    public void populate() {
    }


}
