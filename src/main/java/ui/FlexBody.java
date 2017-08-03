/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import db.news.FlexUser;
import db.news.GraphEntity;
import db.news.NewsArticle;
import java.util.Collection;
import java.util.HashSet;
import ui.news.article.FlexPanel;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class FlexBody extends FlexPanel {
    private final FlexUser user;
    
    public FlexBody(FlexUser user) {
        this.user = user;
        super.addStyleName("flex-body");
        super.setSizeFull();
        initTimerTask(DataProviderType.LATEST, null);
    }
    
    private void initTimerTask(DataProviderType type, String value) {
            super.setContent(new MasterDetailView());
            
            /* Update body with views for new items */
            new Thread(() -> {
                System.out.println("FlexBodyThread#run(): START");
                
                loadNodes(type, value).forEach(item -> {
                    addItemView(item);
                    try {
                        Thread.sleep(500);
                    } catch(InterruptedException ie) {
                        System.err.println(ie.getMessage());
                        return;
                    }
                });
                System.out.println("FlexBodyThread#run(): DONE");
            }).start();  
    }
    
    private Collection<NewsArticle> loadNodes(DataProviderType dataProviderType, String value) {
        Collection<NewsArticle> nodes = new HashSet<>();

        if(dataProviderType == DataProviderType.LATEST) {
            nodes.addAll(ServiceLocator.getInstance().findArticlesService().findLatest(user.getUsername()));
        }
        else if(dataProviderType == DataProviderType.OLDEST) {
            nodes.addAll(ServiceLocator.getInstance().findArticlesService().findOldest(user.getUsername()));
        }
        else if(dataProviderType == DataProviderType.READ) {
            nodes.addAll(ServiceLocator.getInstance().findArticlesService().findAllRead(user.getUsername()));
        }
        else if(dataProviderType == DataProviderType.FAVORITE) {
            nodes.addAll(ServiceLocator.getInstance().findArticlesService().findAllFavorite(user.getUsername()));
        }
        else if(dataProviderType == DataProviderType.FAKE) {
            nodes.addAll(ServiceLocator.getInstance().findArticlesService().findAllFake(user.getUsername()));
        }
        else if(dataProviderType == DataProviderType.CATEGORY && value != null) {
            nodes.addAll(ServiceLocator.getInstance().findArticlesService().findArticlesWithCategory(user.getUsername(), value));
        }
        else if(dataProviderType == DataProviderType.PUBLISHER && value != null) {
            nodes.addAll(ServiceLocator.getInstance().findArticlesService().findArticlesWithSource(user.getUsername(), value));
        }

        return nodes;
    }    

        
    @Override
    public MasterDetailView getContent() {
        return (MasterDetailView) super.getContent();
    }

    public MasterDetailView getMasterDetail() {
        if(isMasterDetailView()) {
            return (MasterDetailView) getContent();
        }
        return null;
    }

    public boolean isMasterDetailView() {
        return getContent() instanceof MasterDetailView;
    }
    
    public void addItemView(GraphEntity item) {
        if(getUI() != null) {
            getUI().access(() -> {
                getContent().addComponent(FlexViewFactory.getInstance().createView(user, item));
            });
        }
    }

    public FlexUser getUser() {
        return user;
    }

    public void updateDataProvider(DataProviderType dataProviderType, String value) {
        initTimerTask(dataProviderType, value);
    }

}
