/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.body;

import db.FlexUser;
import db.NewsArticle;
import factory.FlexViewFactory;
import java.util.Collection;

/**
 *
 * @author zua
 */
public class MasterDetailViewWorker extends Thread {

    private final FlexUser user;
    private final MasterDetailView masterDetailView;
    private final Collection<NewsArticle> nodes;

    public MasterDetailViewWorker(FlexUser user, MasterDetailView masterDetailView, Collection<NewsArticle> nodes) {
        this.user = user;
        this.masterDetailView = masterDetailView;
        this.nodes = nodes;
    }

    @Override
    public void run() {
        if(masterDetailView.getUI() != null && masterDetailView.getUI().isAttached()) {
            masterDetailView.getUI().access(() -> {
                nodes.forEach(item -> {
                    masterDetailView.addComponent(FlexViewFactory.getInstance().createArticleView(user, item));
                });
            });
        }
    }
    
    
            
    
}
