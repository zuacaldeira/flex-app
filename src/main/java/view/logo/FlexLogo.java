/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.logo;

import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;

/**
 *
 * @author zua
 */
public class FlexLogo extends HorizontalLayout {

    private static final long serialVersionUID = 8041620231458973471L;

    private Image image;
    private Label ngutu;
    
    public FlexLogo() {
        initLabel();
        initImage();
        super.addComponents(image, ngutu);
        super.setComponentAlignment(ngutu, Alignment.MIDDLE_CENTER);
        super.setSizeUndefined();
        super.setMargin(new MarginInfo(false, false, false, true));
        super.setSpacing(false);
    }

    private void initLabel() {
        ngutu = new Label("Ngutu.org");
        ngutu.setStyleName("flex-logo");
    }

    private void initImage() {
        image = new Image(null, new ThemeResource("logo1.png"));
        image.setWidth("64px");
        image.setHeight("64px");
    }
}
