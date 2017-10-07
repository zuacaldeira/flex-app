/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.body;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Image;
import data.DataProviderType;
import grid.MasterDetailView;
import factory.FlexViewFactory;
import db.FlexUser;
import db.GraphEntity;
import db.NewsArticle;
import java.util.Collection;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import panel.FlexPanel;
import utils.MyDateUtils;
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
            return ServiceLocator.getInstance().findArticlesService().findLatest(user.getUsername());
        } else if (dataProviderType == DataProviderType.OLDEST) {
            return ServiceLocator.getInstance().findArticlesService().findOldest(user.getUsername());
        } else if (dataProviderType == DataProviderType.READ) {
            return ServiceLocator.getInstance().findArticlesService().findAllRead(user.getUsername());
        } else if (dataProviderType == DataProviderType.FAVORITE) {
            return ServiceLocator.getInstance().findArticlesService().findAllFavorite(user.getUsername());
        } else if (dataProviderType == DataProviderType.FAKE) {
            return ServiceLocator.getInstance().findArticlesService().findAllFake(user.getUsername());
        } else if (dataProviderType == DataProviderType.CATEGORY && value != null) {
            return ServiceLocator.getInstance().findArticlesService().findArticlesWithCategory(user.getUsername(), getCategoryDBCaption(value));
        } else if (dataProviderType == DataProviderType.PUBLISHER && value != null) {
            return ServiceLocator.getInstance().findArticlesService().findArticlesWithSource(user.getUsername(), getSourceIdForSourceName(value));
        } else if (dataProviderType == DataProviderType.LANGUAGES && value != null) {
            return ServiceLocator.getInstance().findArticlesService().findArticlesWithLanguage(user.getUsername(), MyDateUtils.getLanguageCode(value));
        } else if (dataProviderType == DataProviderType.COUNTRIES && value != null) {
            return ServiceLocator.getInstance().findArticlesService().findArticlesWithCountry(user.getUsername(), MyDateUtils.getCountryCode(value));
        } else if (dataProviderType == DataProviderType.SEARCH && value != null) {
            return ServiceLocator.getInstance().findArticlesService().findArticlesWithText(value);
        }
        return ServiceLocator.getInstance().findArticlesService().findLatest(user.getUsername());
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
                    i++;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(FlexBody.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        bodyWorker.start();
        System.out.println("FlexBodyThread#run(): DONE");
    }

    private String getCountryDBCaption(String displayCountry) {
        for(Locale l: Locale.getAvailableLocales()) {
            if(l.getDisplayCountry().equalsIgnoreCase(displayCountry)) {
                System.out.println("Found Locale with country " + l.getDisplayCountry());
                return l.getCountry();
            }
        }
        throw new IllegalArgumentException("Unknown country " + displayCountry);
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

    private void cleanUp() {
        initMasterDetail();
        setContent(masterDetailView);
    }

    private String getSourceIdForSourceName(String value) {
        return ServiceLocator.getInstance().findSourcesService().findSourceNamed(value).getSourceId();
    }

}
