/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import utils.UIUtils;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Image;
import data.DataProviderType;
import grid.MasterDetailView;
import factory.FlexViewFactory;
import db.FlexUser;
import db.GraphEntity;
import db.NewsArticle;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import panel.FlexPanel;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class FlexBody extends FlexPanel {

    private final FlexUser user;
    private MasterDetailView masterDetailView;

    private transient Thread bodyWorker;

    public FlexBody(FlexUser user) {
        this.user = user;
        super.addStyleName("flex-body");
        super.setWidth("100%");
        super.setHeightUndefined();
        super.addDetachListener(e -> {
            if (bodyWorker != null && bodyWorker.isAlive()) {
                bodyWorker.interrupt();
            }
        });
        cleanUp();
        updateData(DataProviderType.LATEST, "latest");
    }

    private Collection<NewsArticle> loadNodes(DataProviderType dataProviderType, String value) {
        if (dataProviderType == DataProviderType.LATEST) {
            return new ServiceLocator().findArticlesService().findLatest(user.getUsername());
        } else if (dataProviderType == DataProviderType.OLDEST) {
            return new ServiceLocator().findArticlesService().findOldest(user.getUsername());
        } else if (dataProviderType == DataProviderType.READ) {
            return new ServiceLocator().findArticlesService().findAllRead(user.getUsername());
        } else if (dataProviderType == DataProviderType.FAVORITE) {
            return new ServiceLocator().findArticlesService().findAllFavorite(user.getUsername());
        } else if (dataProviderType == DataProviderType.FAKE) {
            return new ServiceLocator().findArticlesService().findAllFake(user.getUsername());
        } else if (dataProviderType == DataProviderType.CATEGORY && value != null) {
            return new ServiceLocator().findArticlesService().findArticlesWithCategory(user.getUsername(), getCategoryDBCaption(value));
        } else if (dataProviderType == DataProviderType.PUBLISHER && value != null) {
            return new ServiceLocator().findArticlesService().findArticlesWithSource(user.getUsername(), value);
        } else if (dataProviderType == DataProviderType.LANGUAGES && value != null) {
            return new ServiceLocator().findArticlesService().findArticlesWithLanguage(user.getUsername(), getLanguageDBCaption(value));
        } else if (dataProviderType == DataProviderType.COUNTRIES && value != null) {
            return new ServiceLocator().findArticlesService().findArticlesWithCountry(user.getUsername(), getCountryDBCaption(value));
        } else if (dataProviderType == DataProviderType.SEARCH && value != null) {
            return new ServiceLocator().findArticlesService().findArticlesWithText(user.getUsername(), value);
        }
        return new ServiceLocator().findArticlesService().findLatest(user.getUsername());
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

    public void updateData(DataProviderType type, String value) {
        initBodyUpdaterThread(type, value);
    }

    private void initBodyUpdaterThread(DataProviderType type, String value) {
        System.out.println("FlexBodyThread#run(): START");
        if (bodyWorker != null) {
            bodyWorker.interrupt();
        }

        if (type == DataProviderType.OVERVIEW) {
            toOverview();
        } else if (type == DataProviderType.MASTER_DETAIL) {
            toMasterDetail();
        } else {

            /* Update body with views for new items */
            bodyWorker = new Thread(() -> {
                Collection<NewsArticle> nodes = loadNodes(type, value);
                cleanUp();
                int i = 0;
                try {
                    for (GraphEntity item : nodes) {
                        if (getUI() != null && getUI().isAttached()) {
                            getUI().access(() -> {
                                addItemView(item);
                            });
                        }
                        if (i % 10 == 0) {
                            Thread.sleep(1000);
                        }
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(FlexBody.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            bodyWorker.start();
        }
        System.out.println("FlexBodyThread#run(): DONE");
    }

    

    private String getLanguageDBCaption(String displayLanguage) {
        String[] parts = displayLanguage.split("-");
        return parts[0].trim();
    }

    private String getCountryDBCaption(String displayCountry) {
        String[] parts = displayCountry.split("-");
        return parts[0].trim();
    }

    private String getCategoryDBCaption(String cat) {
        if (!cat.isEmpty()) {
            char c = cat.charAt(0);
            StringBuilder builder = new StringBuilder(cat.trim());
            builder = builder.replace(0, 1, String.valueOf(Character.toLowerCase(c)));
            String result = builder.toString();
            System.out.println(result);
            result = result.replace(" ", "-");
            System.out.println(result);
            return result;
        }
        return cat;
    }

    private Image initImage(NewsArticle item) {
        if (item.getImageUrl() != null) {
            Image image = new Image(null, new ExternalResource(item.getImageUrl()));
            image.setStyleName("image");
            image.setSizeFull();
            return image;
        }
        return new Image();
    }

    private void initMasterDetail() {
        masterDetailView = new MasterDetailView();
        masterDetailView.setWidth("100%");
    }

    private void toOverview() {
        masterDetailView.getBrowserFrame().setVisible(false);
    }

    private void toMasterDetail() {
        masterDetailView.getBrowserFrame().setVisible(true);
    }

    private void cleanUp() {
        initMasterDetail();
        setContent(masterDetailView);
    }

}
