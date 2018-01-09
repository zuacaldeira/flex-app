/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;

/**
 *
 * @author zua
 */
public class ExternalMasterDetailView extends MasterDetailView {

    private static final long serialVersionUID = 4681482059615344025L;

    @Override
    protected Component createTargetView() {
        return null;
    }

    @Override
    protected void updateTarget(String url) {
        Notification.show("Updating url in external target");
    }


}
