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
import data.ArticlesRepository;
import db.NewsArticle;
import io.reactivex.Observable;

/**
 *
 * @author zua
 */
public class NewsBody extends FlexPanel {

    private static final long serialVersionUID = 6273025631274336910L;

    private FlexUser user;
    private MasterDetailView masterDetailView;
    
    public NewsBody() {
        initUser();
        this.initMasterDetailView();
        super.addStyleName("flex-body");
        super.setSizeFull();
    }

    private void initUser() {
        if (UI.getCurrent() != null) {
            System.out.println("Found USER -> " + UI.getCurrent().getSession().getAttribute("user"));
            user = (FlexUser) UI.getCurrent().getSession().getAttribute("user");
            System.out.println("NEWS VIEW USER -> " + user);
        }
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
        return user;
    }

    public void populate(DataProviderType type, String value) {
        System.out.println("FlexBodyThread#run(): START");
        masterDetailView.refresh(type, value);
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
