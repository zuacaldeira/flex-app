/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import com.vaadin.icons.VaadinIcons;

/**
 *
 * @author zua
 */
public class NgutuVisionCard extends NgutuCard {

    private static final long serialVersionUID = -1545413488995124642L;

    public NgutuVisionCard() {
        super("Vision", getVisionText(), VaadinIcons.ROAD);
    }

    private static String getVisionText() {
        return "Ngutu's vision to connect the unconnected dots is to provide "
                + "every human being with a passive online presence that "
                + "enforces the granted rights as 1) full world citizens, 2) informed "
                + "professionals and 3) sucessfull entrepeneurs. Because that is "
                + "what we all are.";
    }

}
