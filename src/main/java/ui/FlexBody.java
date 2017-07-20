/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.ui.AbstractComponentContainer;
import com.vaadin.ui.UIDetachedException;
import db.news.FlexUser;
import db.news.GraphEntity;
import java.util.Date;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
import ui.news.article.FlexPanel;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class FlexBody extends FlexPanel {
    private FlexUser user;
    private AbstractComponentContainer bodyContent;
    private DataProviderType dataProviderType;
    private String dataProviderValue;
    
    public FlexBody(FlexUser user) {
        super.addStyleName("flex-body");
        this.user = user;
        bodyContent = new MasterDetailView();
        this.dataProviderType = DataProviderType.LATEST;
        super.setContent(bodyContent);
        super.setSizeFull();
    }
    
    public void attach() {
        super.attach();
        initTimerTask();
    }
    
    private void initTimerTask() {
        new Timer().scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                try {
                    if(getUI() != null) {
                        getUI().access(() -> {
                            getNodes().forEach(item -> {
                                addItemView(item);
                                try {
                                    Thread.sleep(500);
                                } catch(InterruptedException ie) {
                                    System.err.println(ie.getMessage());
                                    return;
                                }
                            });
                        });
                    }
                } catch(UIDetachedException udx) {
                    // TODO: nothing
                }
            }
        }, new Date(), 60000);
    }
    
    private Iterable<? extends GraphEntity> getNodes() {
        
        if(dataProviderType == DataProviderType.LATEST) {
            return ServiceLocator.getInstance().findArticlesService().findLatest(user.getUsername());
        }
        else if(dataProviderType == DataProviderType.OLDEST) {
            return ServiceLocator.getInstance().findArticlesService().findOldest(user.getUsername());
        }
        else if(dataProviderType == DataProviderType.READ) {
            return ServiceLocator.getInstance().findArticlesService().findAllRead(user.getUsername());
        }
        else if(dataProviderType == DataProviderType.FAVORITE) {
            return ServiceLocator.getInstance().findArticlesService().findAllFavorite(user.getUsername());
        }
        else if(dataProviderType == DataProviderType.FAKE) {
            return ServiceLocator.getInstance().findArticlesService().findAllFake(user.getUsername());
        }
        else if(dataProviderType == DataProviderType.CATEGORY && dataProviderValue != null) {
            return ServiceLocator.getInstance().findArticlesService().findArticlesWithCategory(user.getUsername(), dataProviderValue);
        }
        else if(dataProviderType == DataProviderType.PUBLISHER && dataProviderValue != null) {
            return ServiceLocator.getInstance().findArticlesService().findArticlesWithSource(user.getUsername(), dataProviderValue);
        }
        return new HashSet<>();
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

    public void setDataProviderType(DataProviderType dataProviderType, String value) {
        this.dataProviderType = dataProviderType;
        this.dataProviderValue = value;
    }

}
