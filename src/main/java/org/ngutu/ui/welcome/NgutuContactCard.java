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
public class NgutuContactCard extends NgutuCard {

    private static final long serialVersionUID = -7189706877692676108L;

    public NgutuContactCard() {
        super("Contact Ngutu.org", getContactText(), VaadinIcons.AT);
    }

    private static String getContactText() {
        return "Don't let your questions unanswered. We're pleased to answer "
                + "any question. <a href=\"mailto:info@ngutu.org\">info@ngutu.org</a>";
    }

}
