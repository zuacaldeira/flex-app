/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.viewproviders;

import com.vaadin.navigator.Navigator.ClassBasedViewProvider;
import view.main.FlexBooksView;

/**
 *
 * @author zua
 */
public class BooksViewProvider extends ClassBasedViewProvider {

    private static final long serialVersionUID = -7126689158013262916L;

    public BooksViewProvider() {
        super(FlexViews.BOOKS, FlexBooksView.class);
    }
    
    
}
