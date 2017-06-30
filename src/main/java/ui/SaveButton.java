/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author zua
 */
public class SaveButton extends FlexButton {

    public SaveButton() {
        super("Save", VaadinIcons.CHECK);
        setStyleName(ValoTheme.BUTTON_PRIMARY);
    }
    
}
