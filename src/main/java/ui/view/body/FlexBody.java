/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.body;

import data.DataProviderType;
import factory.FlexViewFactory;
import db.FlexUser;
import db.GraphEntity;
import panel.FlexPanel;
import ui.view.menu.CanPopulate;

/**
 *
 * @author zua
 */
public class FlexBody extends FlexPanel implements CanPopulate {

    private static final long serialVersionUID = 6273025631274336910L;

    private final FlexUser user;
    private MasterDetailView masterDetailView;
    private FlexBodyWorker worker;

    public FlexBody(FlexUser user) {
        this.user = user;
        this.initMasterDetailView();
        super.addStyleName("flex-body");
        super.setWidth("100%");
        super.setHeightUndefined();
        super.addDetachListener(event -> {
            if (worker != null) {
                worker.interrupt();
            }
        });
    }

    private void initMasterDetailView() {
        masterDetailView = new MasterDetailView(user);
        masterDetailView.setWidth("100%");
        setContent(masterDetailView);
    }

    @Override
    public MasterDetailView getContent() {
        return (MasterDetailView) super.getContent();
    }

    public MasterDetailView getMasterDetail() {
        return masterDetailView;
    }

    public void addItemView(GraphEntity item) {
        getUI().access(() -> {
            masterDetailView.addComponent(FlexViewFactory.getInstance().createView(user, item));
        });
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
        if (worker != null) {
            worker.interrupt();
        }
        worker = new FlexBodyWorker(user, this, type, value);
        worker.start();
        System.out.println("FlexBodyThread#run(): DONE");
    }

}
