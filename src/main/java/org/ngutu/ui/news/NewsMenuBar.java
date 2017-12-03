/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.ValoTheme;
import db.FlexUser;
import data.DataProviderType;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.NewsSourceServiceInterface;
import utils.MyDateUtils;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public final class NewsMenuBar extends MenuBar {

    private static final long serialVersionUID = -1299703352057116843L;

    private final FlexUser user;
    private final NewsSourceServiceInterface sourcesService;

    // Main Menu (top level)
    private MenuBarThread worker;
    private MenuItem news;
    private MenuItem categories;
    private MenuItem publishers;
    private MenuItem languages;
    private MenuItem countries;

    private MenuItem read;
    private MenuItem favorite;
    private MenuItem fake;
    private MenuItem oldest;
    private MenuItem latest;
    private MenuItem full;
    private MenuItem imagesOnly;
    private MenuItem titlesOnly;

    public NewsMenuBar(FlexUser user) {
        this.user = user;
        sourcesService = ServiceLocator.getInstance().findSourcesService();
        worker = new MenuBarThread();
        this.initMenuItems();
        super.addDetachListener(event -> {
            interruptWorker();
        });
        refresh();
    }

    protected void initMenuItems() {
        news = addItem("Articles", null, null);
        publishers = addItem("Publishers", null, null);
        categories = addItem("Categories", null, null);
        languages = addItem("Languages", null, null);
        countries = addItem("Countries", null, null);
        setSizeUndefined();
        setAutoOpen(true);
        setStyleName("news-menu-bar");
        addStyleName(ValoTheme.MENUBAR_BORDERLESS);
    }

    private void refresh() {
        interruptWorker();
        worker = new MenuBarThread();
        worker.start();
    }

    private void interruptWorker() {
        if (worker != null) {
            worker.interrupt();
        }
    }

    protected void populateNewsCategory() {
        getUI().access(() -> {
            if (categories != null) {
                sourcesService.findCategories().subscribe(cat -> {
                    categories.addItem(getCategoryCaption(cat), (selectedMenuItem) -> {
                        updateBody(DataProviderType.CATEGORY, selectedMenuItem.getText());
                    });
                });
            }
        });
    }

    protected void populateNewsPublisher() {
        getUI().access(() -> {
            if (publishers != null) {
                sourcesService.findNames().subscribe(name -> {
                    publishers.addItem(name, (selectedMenuItem -> {
                        updateBody(DataProviderType.PUBLISHER, selectedMenuItem.getText());
                    }));
                });
            }
        });
    }

    protected void populateNewsLanguages() {
        getUI().access(() -> {
            if (languages != null) {
                sourcesService.findLocales().subscribe(localeString -> {
                    if (localeString != null && !localeString.isEmpty()) {
                        String lang = MyDateUtils.getLanguageNameFromPattern(localeString);
                        languages.addItem(lang, (selectedMenuItem) -> {
                            updateBody(DataProviderType.LANGUAGES, selectedMenuItem.getText());
                        });
                    }
                });
            }
        });
    }

    protected void populateNewsCountries() {
        getUI().access(() -> {
            if (countries != null) {
                sourcesService.findLocales().subscribe(localeString -> {
                    if (localeString != null && !localeString.isEmpty()) {
                        String country = MyDateUtils.getCountryNameFromPattern(localeString);
                        countries.addItem(country, (selectedMenuItem) -> {
                            updateBody(DataProviderType.LANGUAGES, selectedMenuItem.getText());
                        });
                    }
                });
            }
        });
    }

    private void populateViews() {
        getUI().access(() -> {
            if (news != null) {
                NewsBody body = ((NewsView) getUI().getContent()).getBody();
                MasterDetailView masterDetail = body.getMasterDetail();
                full = news.addItem("Full", (selectedItem) -> {
                    masterDetail.full();
                });
                imagesOnly = news.addItem("Images Only", (selectedItem) -> {
                    masterDetail.imagesOnly();
                });
                titlesOnly = news.addItem("Titles Only", (selectedItem) -> {
                    masterDetail.titlesOnly();
                });
            }
        });
    }

    private void populateNewsOverviews() {
        populateNewsByTime();
        news.addSeparator();
        populateNewsByStatus();
    }

    private void populateNewsByTime() {
        getUI().access(() -> {
            if (news != null && getUI() != null) {
                latest = news.addItem("Latest", (selectedItem) -> {
                    updateBody(DataProviderType.LATEST, null);
                });
                oldest = news.addItem("Oldest", (selectedItem) -> {
                    updateBody(DataProviderType.OLDEST, null);
                });
            }
        });
    }

    private void populateNewsByStatus() {
        getUI().access(() -> {
            if (user != null) {
                read = news.addItem("Read", VaadinIcons.EYE_SLASH, (selectedMenuItem) -> {
                    updateBody(DataProviderType.READ, null);
                });
                favorite = news.addItem("Favorite", VaadinIcons.STAR, (selectedMenuItem) -> {
                    updateBody(DataProviderType.FAVORITE, null);
                });
                fake = news.addItem("Fake", VaadinIcons.EXCLAMATION_CIRCLE, (selectMenuItem) -> {
                    updateBody(DataProviderType.FAKE, null);
                });
            }
        });
    }

    private void updateBody(DataProviderType dataType, String value) {
        getUI().access(() -> {
            NewsBody body = ((NewsView) getUI().getNavigator().getCurrentView()).getBody();
            body.populate(dataType, value);
        });
    }

    private String getCategoryCaption(String cat) {
        char c = cat.charAt(0);
        return cat.replaceFirst(
                String.valueOf(c),
                String.valueOf(Character.toUpperCase(c)))
                .replace("-", " ");
    }

    public MenuItem getNews() {
        return news;
    }

    public MenuItem getCategories() {
        return categories;
    }

    public MenuItem getPublishers() {
        return publishers;
    }

    public MenuItem getLanguages() {
        return languages;
    }

    public MenuItem getCountries() {
        return countries;
    }

    public NewsSourceServiceInterface getSourcesService() {
        return sourcesService;
    }

    public MenuItem getRead() {
        return read;
    }

    public MenuItem getFavorite() {
        return favorite;
    }

    public MenuItem getFake() {
        return fake;
    }

    public MenuItem getOldest() {
        return oldest;
    }

    public MenuItem getLatest() {
        return latest;
    }

    public MenuItem getFull() {
        return full;
    }

    public MenuItem getImagesOnly() {
        return imagesOnly;
    }

    public MenuItem getTitlesOnly() {
        return titlesOnly;
    }

    public FlexUser getUser() {
        return user;
    }

    private class MenuBarThread extends Thread {

        public MenuBarThread() {
        }

        @Override
        public void run() {
            populateNewsOverviews();
            populateNewsPublisher();
            populateNewsCategory();
            populateNewsLanguages();
            populateNewsCountries();
        }

    }
}
