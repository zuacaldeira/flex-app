/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.icons.VaadinIcons;

/**
 *
 * @author zua
 */
public class DeleteButton extends FlexButton {

    public DeleteButton() {
        super("Delete", VaadinIcons.TRASH);
        setDescription("Delete");
    }
    
}
