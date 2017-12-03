/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import data.DataProviderType;
import db.FlexUser;
import components.FlexPanel;
import data.ArticlesRepository;
import db.NewsArticle;
import io.reactivex.Observable;

/**
 *
 * @author zua
 */
public class NewsBody extends FlexPanel {

    private static final long serialVersionUID = 6273025631274336910L;

    private final FlexUser user;
    private MasterDetailView masterDetailView;
    
    public NewsBody(FlexUser user) {
        this.user = user;
        this.initMasterDetailView();
        super.addStyleName("flex-body");
        super.setSizeFull();
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

    public void populate(DataProviderType type, String value) {
        System.out.println("FlexBodyThread#run(): START");
        masterDetailView.refresh(user, type, value);
        System.out.println("FlexBodyThread#run(): DONE");
    }
    
    
    private Observable<NewsArticle> getNodes(DataProviderType type, String value) {
        Observable<NewsArticle> nodes = null;
        if(user != null && value != null) {
            nodes = new ArticlesRepository().loadNodes(type, value, user);
        }
        else if(user != null && value == null) {
            nodes = new ArticlesRepository().loadNodes(type, user);
        }
        else if(user == null && value != null) {
            nodes = new ArticlesRepository().loadNodes(type, value);
        }
        else if(user == null && value == null) {
            nodes = new ArticlesRepository().loadNodes(type);
        }
        return nodes;
    }

}
