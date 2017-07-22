/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.ui.UIDetachedException;
import db.news.FlexUser;
import db.news.GraphEntity;
import db.news.NewsArticle;
import java.util.Timer;
import java.util.TimerTask;
import ui.news.article.FlexPanel;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class FlexBody extends FlexPanel {
    private final FlexUser user;
    private DataProviderType dataProviderType;
    private String dataProviderValue;
    private Timer timer;
    private Iterable<NewsArticle> nodes;
    
    public FlexBody(FlexUser user) {
        super.addStyleName("flex-body");
        this.user = user;
        this.dataProviderType = DataProviderType.LATEST;
        super.setContent(new MasterDetailView());
        super.setSizeFull();
        initTimerTask();
    }
    
    private void initTimerTask() {
        timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                try {
                    if(getUI() != null) {
                        System.out.println("Running scheduled timer task with data provider type");
                        System.out.println(dataProviderType);
                        
                        loadNodes();
                        nodes.forEach(item -> {
                            getUI().access(() -> {
                                addItemView(item);
                            });
                            try {
                                Thread.sleep(500);
                            } catch(InterruptedException ie) {
                                System.err.println(ie.getMessage());
                                return;
                            }
                        });
                    }
                } catch(UIDetachedException udx) {
                    // TODO: nothing
                }
            }
        }, 1000, 60000);
    }
    
    private void loadNodes() {
        
        if(dataProviderType == DataProviderType.LATEST) {
            nodes = ServiceLocator.getInstance().findArticlesService().findLatest(user.getUsername());
        }
        else if(dataProviderType == DataProviderType.OLDEST) {
            nodes = ServiceLocator.getInstance().findArticlesService().findOldest(user.getUsername());
        }
        else if(dataProviderType == DataProviderType.READ) {
            nodes = ServiceLocator.getInstance().findArticlesService().findAllRead(user.getUsername());
        }
        else if(dataProviderType == DataProviderType.FAVORITE) {
            nodes = ServiceLocator.getInstance().findArticlesService().findAllFavorite(user.getUsername());
        }
        else if(dataProviderType == DataProviderType.FAKE) {
            nodes = ServiceLocator.getInstance().findArticlesService().findAllFake(user.getUsername());
        }
        else if(dataProviderType == DataProviderType.CATEGORY && dataProviderValue != null) {
            nodes = ServiceLocator.getInstance().findArticlesService().findArticlesWithCategory(user.getUsername(), dataProviderValue);
        }
        else if(dataProviderType == DataProviderType.PUBLISHER && dataProviderValue != null) {
            nodes = ServiceLocator.getInstance().findArticlesService().findArticlesWithSource(user.getUsername(), dataProviderValue);
        }
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
        getContent().addComponent(FlexViewFactory.getInstance().createView(user, item));
    }

    public FlexUser getUser() {
        return user;
    }

    public void setDataProviderType(DataProviderType dataProviderType, String value) {
        if(timer != null) {
            timer.cancel();
        }

        this.dataProviderType = dataProviderType;
        this.dataProviderValue = value;        
        initTimerTask();
    }

}
