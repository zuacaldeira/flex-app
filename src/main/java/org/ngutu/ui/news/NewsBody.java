/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import data.DataProviderType;
import db.FlexUser;
import components.FlexPanel;
import components.CanPopulate;

/**
 *
 * @author zua
 */
public class NewsBody extends FlexPanel implements CanPopulate {

    private static final long serialVersionUID = 6273025631274336910L;

    private final FlexUser user;
    private MasterDetailView masterDetailView;
    private transient FlexBodyWorker worker;
    
    public NewsBody(FlexUser user) {
        this.user = user;
        this.initMasterDetailView();
        super.addStyleName("flex-body");
        super.setSizeFull();
        super.addDetachListener(e -> {
            if(worker != null) {
                worker.interrupt();
            }
        });
    }

    private void initMasterDetailView() {
        masterDetailView = new MasterDetailView(user);
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
        return user;
    }

    @Override
    public void populate() {
        this.populate(DataProviderType.LATEST, "latest");
    }

    public void populate(DataProviderType type, String value) {
        System.out.println("FlexBodyThread#run(): START");
        initMasterDetailView();
        if(worker != null) {
            worker.interrupt();
        }
        worker = new FlexBodyWorker(user, masterDetailView, type, value);
        worker.start();
        System.out.println("FlexBodyThread#run(): DONE");
    }

}
