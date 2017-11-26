/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import data.DataProviderType;
import db.FlexUser;
import components.FlexPanel;
import org.ngutu.ui.news.FlexBodyWorker;
import org.ngutu.ui.news.MasterDetailView;
import view.menu.CanPopulate;

/**
 *
 * @author zua
 */
public class WelcomeBody extends FlexPanel implements CanPopulate {

    private static final long serialVersionUID = 6273025631274336910L;

    private final FlexUser user;
    private MasterDetailView masterDetailView;

    public WelcomeBody(FlexUser user) {
        this.user = user;
        this.initMasterDetailView();
        super.addStyleName("flex-body");
        super.setSizeFull();
    }

    private void initMasterDetailView() {
        masterDetailView = new MasterDetailView(user);
        masterDetailView.setSizeFull();
        masterDetailView.getInfoFrame().setVisible(false);
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
        new FlexBodyWorker(user, masterDetailView, type, value).start();
        System.out.println("FlexBodyThread#run(): DONE");
    }

}
