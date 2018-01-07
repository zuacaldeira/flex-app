/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.books;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.themes.ValoTheme;
import org.ngutu.ui.common.FlexButton;

/**
 *
 * @author zua
 */
public class BooksButton extends FlexButton {

    private static final long serialVersionUID = -8890498557214281725L;

    public BooksButton() {
        super(VaadinIcons.BOOK);
        super.addStyleName("books-button");
        super.addStyleName(ValoTheme.BUTTON_FRIENDLY);
    }
    
}
