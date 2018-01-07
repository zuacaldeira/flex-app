/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.books;

import org.ngutu.ui.common.AbstractBody;
import org.ngutu.ui.common.AbstractMenu;
import org.ngutu.ui.common.AbstractView;

/**
 *
 * @author zua
 */
public class BooksView extends AbstractView {

    private static final long serialVersionUID = 3485805360419205354L;

    
    public BooksView() {
    }

    @Override
    protected AbstractMenu createMenu() {
        return new BooksMenu();
    }

    @Override
    protected AbstractBody createBody() {
        return new BooksBody();
    }


}
