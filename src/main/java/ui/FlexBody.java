/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.ui.Notification;
import db.news.FlexUser;
import db.news.GraphEntity;
import db.news.NewsArticle;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.news.article.FlexPanel;
import utils.FlexUIUtils;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class FlexBody extends FlexPanel {
    private final FlexUser user;
    private transient Thread bodyWorker;
    
    public FlexBody(FlexUser user) {
        this.user = user;
        super.addStyleName("flex-body");
        super.setSizeFull();
        updateData(DataProviderType.LATEST, "latest");
    }
    
    private Collection<NewsArticle> loadNodes(DataProviderType dataProviderType, String value) {
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
        else if(dataProviderType == DataProviderType.CATEGORY && value != null) {
            return ServiceLocator.getInstance().findArticlesService().findArticlesWithCategory(user.getUsername(), getCategoryDBCaption(value));
        }
        else if(dataProviderType == DataProviderType.PUBLISHER && value != null) {
            return ServiceLocator.getInstance().findArticlesService().findArticlesWithSource(user.getUsername(), value);
        }
        else if(dataProviderType == DataProviderType.LANGUAGES && value != null) {
            return ServiceLocator.getInstance().findArticlesService().findArticlesWithLanguage(user.getUsername(), getLanguageDBCaption(value));
        }
        else if(dataProviderType == DataProviderType.SEARCH && value != null) {
            return ServiceLocator.getInstance().findArticlesService().findArticlesWithText(user.getUsername(), value);
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
        if(getUI() != null && getUI().isAttached()) {
            getUI().access(() -> {
                getContent().addComponent(FlexViewFactory.getInstance().createView(user, item));
            });
        }
    }

    public FlexUser getUser() {
        return user;
    }

    public void updateData(DataProviderType type, String value) {
        super.setContent(new MasterDetailView());
        if(FlexUIUtils.getInstance().getMainView(this) != null && value != null) {
            //FlexUtils.getInstance().getMainView(this).getMenu().getLogoBar().setNavigationContext(value);
        }
        initBodyUpdaterThread(type, value);
    }

    private void initBodyUpdaterThread(DataProviderType type, String value) {
        if(bodyWorker != null) {
            bodyWorker.interrupt();
        }
        /* Update body with views for new items */
        bodyWorker = new Thread(() -> {
            System.out.println("FlexBodyThread#run(): START");
                
            try {
                Collection<NewsArticle> nodes = loadNodes(type, value);
                Thread.sleep(500);
                for(GraphEntity item: nodes) {
                    addItemView(item);
                    Thread.sleep(500);
                };
                System.out.println("FlexBodyThread#run(): DONE");
            } catch (InterruptedException ex) {
                Logger.getLogger(FlexBody.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        bodyWorker.start();  
    }
    
    private String getLanguageDBCaption(String displayLanguage) {
        String[] parts = displayLanguage.split("-");
        return parts[0].trim();
    }

    private String getCategoryDBCaption(String cat) {
        if(!cat.isEmpty()) {
            char c = cat.charAt(0);
            StringBuilder builder = new StringBuilder(cat.trim());
            builder = builder.replace(0, 1, String.valueOf(Character.toLowerCase(c)));
            String result = builder.toString();
            System.out.println(result);
            result = result.replace(" ", "-");
            System.out.println(result);
            return  result;
        }
        return cat;
    }

}
