/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import db.AmazonBook;

/**
 *
 * @author zua
 */
public class AmazonBookView extends CssLayout {
    
    private static final long serialVersionUID = -2974379957589801960L;
    
    private final AmazonBook book;

    public AmazonBookView(AmazonBook book) {
        this.book = book;
        super.addComponent(new Label(book.getTitle()));
    }

}
