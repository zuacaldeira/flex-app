/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.icons.VaadinIcons;

/**
 *
 * @author zua
 */
public class FavoriteButton extends FlexButton {

    public FavoriteButton() {
        super(VaadinIcons.STAR);
        setDescription("Favorite");
    }
    
}
