/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import com.vaadin.icons.VaadinIcons;

/**
 *
 * @author zua
 */
public class LoginButton extends FlexButton {

    public LoginButton() {
        super("Login", VaadinIcons.CHECK);
        setSizeUndefined();
    }
    
}
