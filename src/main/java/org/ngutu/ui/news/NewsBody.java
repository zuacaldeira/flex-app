/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.ui.UI;
import data.DataProviderType;
import db.FlexUser;
import components.FlexPanel;

/**
 *
 * @author zua
 */
public class NewsBody extends FlexPanel {

    private static final long serialVersionUID = 6273025631274336910L;

    private MasterDetailView masterDetailView;
    
    public NewsBody() {
        this.initMasterDetailView();
        super.addStyleName("flex-body");
        super.setSizeFull();
    }

    private void initMasterDetailView() {
        masterDetailView = new MasterDetailView();
        masterDetailView.setSizeFull();
        super.setContent(masterDetailView);
    }

    @Override
    public MasterDetailView getContent() {
        return (MasterDetailView) super.getContent();
    }

    public MasterDetailView getMasterDetail() {
        return masterDetailView;
    }

    public FlexUser getUser() {
        if (UI.getCurrent() != null) {
            System.out.println("Found USER -> " + UI.getCurrent().getSession().getAttribute("user"));
            return (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        }
        return null;
    }

    public void populate(DataProviderType type, String value) {
        System.out.println("FlexBodyThread#run(): START");
        initMasterDetailView();
        masterDetailView.refresh(type, value);
        System.out.println("FlexBodyThread#run(): DONE");
    }
    
    

}
