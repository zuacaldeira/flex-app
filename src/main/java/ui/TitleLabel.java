/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.ui.Label;

/**
 *
 * @author zua
 */
public class TitleLabel extends Label {

    public TitleLabel(String title) {
        super(title);
        setStyleName("title");
    }
    
}
