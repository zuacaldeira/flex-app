/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.books;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author zua
 */
public class AmazonProductsGrid extends VerticalLayout {

    private static final long serialVersionUID = -1819373602586688873L;
    
    private final Label campaignName;
    private final ComboBox campaignTarget;
    private final GridLayout body;
    
    public AmazonProductsGrid(String campaing) {
        campaignName = new Label(campaing);
        campaignName.setSizeUndefined();
        
        campaignTarget = new ComboBox();
        campaignTarget.setSizeUndefined();
        campaignTarget.setStyleName(ValoTheme.COMBOBOX_ALIGN_RIGHT);
        
        body = new GridLayout(5, 2);
        body.setSizeFull();
        
        super.setSizeFull();
        super.addComponents(new HorizontalLayout(campaignName, campaignTarget), body);
        super.setExpandRatio(body, 1f);
    }
    
}
