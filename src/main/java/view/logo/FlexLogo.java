/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.logo;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
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

    private Label image;
    private Label ngutu;
    
    public FlexLogo() {
        initLabel();
        initImage();
        super.addComponents(image, ngutu);
        super.setComponentAlignment(ngutu, Alignment.MIDDLE_CENTER);
        super.setSizeUndefined();
        super.setMargin(new MarginInfo(false, false, false, true));
        super.setSpacing(false);
        super.setStyleName("flex-logo");
    }

    private void initLabel() {
        ngutu = new Label("Ngutu");
    }

    private void initImage() {
        image = new Label(VaadinIcons.CLUSTER.getHtml(), ContentMode.HTML);
        image.setWidth("64px");
        image.setHeight("64px");
    }
}
