/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.body;

import data.ArticlesRepository;
import data.DataProviderType;
import factory.FlexViewFactory;
import db.FlexUser;
import db.GraphEntity;
import db.NewsArticle;
import java.util.Collection;
import panel.FlexPanel;

/**
 *
 * @author zua
 */
public class FlexBody extends FlexPanel {

    private static final long serialVersionUID = 6273025631274336910L;

    private final FlexUser user;
    private MasterDetailView masterDetailView;

    public FlexBody(FlexUser user) {
        this.user = user;
        super.addStyleName("flex-body");
        super.setWidth("100%");
        super.setHeightUndefined();
        this.initBodyUpdaterThread(DataProviderType.LATEST, "latest");
    }

    @Override
    public MasterDetailView getContent() {
        return (MasterDetailView) super.getContent();
    }

    public MasterDetailView getMasterDetail() {
        return masterDetailView;
    }

    public void addItemView(GraphEntity item) {
        masterDetailView.addComponent(FlexViewFactory.getInstance().createView(user, item));
    }

    public FlexUser getUser() {
        return user;
    }

    public void initBodyUpdaterThread(DataProviderType type, String value) {
        System.out.println("FlexBodyThread#run(): START");
        bodyCleanUp();
        Collection<NewsArticle> nodes = new ArticlesRepository().loadNodes(type, value, user);
        bodyUpdate(nodes);
        System.out.println("FlexBodyThread#run(): DONE");
    }

    private void initMasterDetail() {
        masterDetailView = new MasterDetailView(user);
        masterDetailView.setWidth("100%");
    }

    protected void cleanUp() {
        initMasterDetail();
        setContent(masterDetailView);
    }

    private void bodyCleanUp() {
        cleanUp();
    }

    private void bodyUpdate(Collection<NewsArticle> nodes) {
        masterDetailView.update(nodes);
    }


}
