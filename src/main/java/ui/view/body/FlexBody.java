/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.body;

import data.ArticlesRepository;
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
        if (bodyWorker != null) {
            bodyWorker.interrupt();
        }
        /* Update body with views for new items */
        bodyWorker = new Thread(() -> {
            cleanUp();
            Collection<NewsArticle> nodes = new ArticlesRepository().loadNodes(type, value, user);
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

    private static String getCountryDBCaption(String displayCountry) {
        for(Locale l: Locale.getAvailableLocales()) {
            if(l.getDisplayCountry().equalsIgnoreCase(displayCountry)) {
                System.out.println("Found Locale with country " + l.getDisplayCountry());
                return l.getCountry();
            }
        }
        throw new IllegalArgumentException("Unknown country " + displayCountry);
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

}
