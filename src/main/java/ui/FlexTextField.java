/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.TextField;

/**
 *
 * @author zua
 */
public class FlexTextField extends TextField {

    public FlexTextField(String caption, VaadinIcons icon) {
        super();
        super.setCaption(caption);
        super.setIcon(icon);
        super.setSizeUndefined();
    }
    
    
}
