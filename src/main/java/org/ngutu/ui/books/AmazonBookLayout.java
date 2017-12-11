/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.books;

import com.vaadin.ui.CssLayout;
import db.books.AmazonBook;

/**
 *
 * @author zua
 */
public class AmazonBookLayout extends CssLayout {

    private static final long serialVersionUID = 952734484265915671L;

    private AmazonBook book;

    public AmazonBookLayout(AmazonBook book) {
        this.book = book;
    }

    public AmazonBook getBook() {
        return book;
    }

    public void setBook(AmazonBook book) {
        this.book = book;
    }
    
    
    
    
    
}
