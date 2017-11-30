/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author zua
 */
public abstract class NgutuCard extends HorizontalLayout {

    private static final long serialVersionUID = -6937281986044130919L;
    private Label image;
    private Label title;
    private Label text;
    private VerticalLayout base;

    public NgutuCard(String title, String caption, VaadinIcons icon) {
        initImage(icon);
        initBase(title, caption);

        super.addComponents(this.image, base);
        super.setExpandRatio(base, 1f);
        
        super.setComponentAlignment(this.image, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(base, Alignment.MIDDLE_CENTER);
        super.setSizeFull();
        super.setSpacing(false);
        super.setMargin(true);
        super.setStyleName("ngutu-card");
    }

    private void initImage(VaadinIcons icon) {
        this.image = new Label(icon.getHtml(), ContentMode.HTML);
        this.image.setStyleName("image");
    }

    private void initBase(String title, String caption) {
        initTitle(title);
        initText(caption);
        base = new VerticalLayout(this.title, this.text);
        base.setWidth("100%");
        base.setSpacing(false);
    }

    private void initTitle(String title) {
        this.title = new Label(title, ContentMode.HTML);
        this.title.setStyleName("subtitle");
    }

    private void initText(String caption) {
        this.text = new Label(caption, ContentMode.HTML);
        this.text.setSizeFull();
    }
}
