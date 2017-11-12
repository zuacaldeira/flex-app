/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.logo;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 *
 * @author zua
 */
public class FlexLogo extends HorizontalLayout {

    private static final long serialVersionUID = 8041620231458973471L;

    private final Label ngutu;
    private final Label dot;
    private final Label org;
    
    public FlexLogo() {
        ngutu = new Label("Ngutu");
        ngutu.setStyleName("ngutu");
        
        dot = new Label(".");
        dot.setStyleName("dot");
        
        org = new Label("org");
        org.setStyleName("org");
        
        super.addComponents(ngutu, dot, org);
        super.setStyleName("flex-logo");
        super.setHeightUndefined();
        super.setMargin(false);
        super.setSpacing(false);
    }
}
