/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.logo;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 *
 * @author zua
 */
public class FlexLogo extends HorizontalLayout {

    private static final long serialVersionUID = 8041620231458973471L;

    private final Label ngutu;
    private final FacebookButton facebookButton;
    private final TwitterButton twitterButton;
    
    public FlexLogo() {
        ngutu = new Label("Ngutu.org");
        ngutu.setStyleName("ngutu");
        
        facebookButton = new FacebookButton();
        twitterButton = new TwitterButton();
        super.addComponents(ngutu, facebookButton, twitterButton);
        super.setComponentAlignment(facebookButton, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(twitterButton, Alignment.MIDDLE_LEFT);
        super.setStyleName("flex-logo");
        super.setSizeUndefined();
        super.setMargin(new MarginInfo(false, false, false, true));
        super.setSpacing(false);
    }
}