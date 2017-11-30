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
public class NgutuGoalsCard extends NgutuCard {

    private static final long serialVersionUID = -1545413488995124642L;

    public NgutuGoalsCard() {
        super("1000 Goals", getGoalsText(), VaadinIcons.BULLSEYE);
    }

    private static String getGoalsText() {
        return "To improve the life os millions of people we must first improve "
                + "the life of thousands. That's why our initial goal is called "
                + "1000's: 1000 schools, 1000 libraries with 1000 books each. "
                + "How we will achieve that? Joins us, and stay tuned.";
    }

}
