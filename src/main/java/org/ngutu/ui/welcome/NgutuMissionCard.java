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
public class NgutuMissionCard extends NgutuCard {

    private static final long serialVersionUID = -7189706877692676108L;

    public NgutuMissionCard() {
        super("Mission", getMissionText(), VaadinIcons.CLUSTER);
    }

    private static String getMissionText() {
        return "Ngutu's mission is to connect the unconnected dots. "
                + "We're commited to bring every person out of social isolation, "
                + "offering them the opportunity to take their share on world development. "
                + "We state clearly that unenployment, poverty, sexism and "
                + "racism are crimes against humanity and the result of a global degenerative mental desease. "
                + "Together, we will make a change for a healthier world with a councious man. For good. "
                + "Together, because we are branches of the same tree: life itself.";
    }

}
