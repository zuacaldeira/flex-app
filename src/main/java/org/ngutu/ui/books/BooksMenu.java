/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.books;

import com.vaadin.ui.UI;
import components.FlexButton;
import db.auth.FlexUser;
import org.ngutu.ui.components.AbstractMenu;
import org.ngutu.ui.news.MenuActions;

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
    public BooksBody getBody() {
        return (BooksBody) ((BooksView) UI.getCurrent().getContent()).getBody();
    }
    
    
    private FlexUser getUser() {
        if (UI.getCurrent() != null) {
            System.out.println("Found USER -> " + UI.getCurrent().getSession().getAttribute("user"));
            return (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        }
        return null;
    }

    @Override
    protected MenuActions createMenuActions() {        
        MenuActions menuActions = new MenuActions();
        FlexButton newsButton = new FlexButton("News");

        menuActions.addComponent(newsButton);
        return menuActions;
    }

}
