/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;
import db.FlexUser;
import java.util.Set;
import data.DataProviderType;
import java.util.Collection;
import java.util.TreeSet;
import services.NewsSourceServiceInterface;
import ui.NewsUI;
import utils.MyDateUtils;
import utils.ServiceLocator;
import view.menu.CanPopulate;

/**
 *
 * @author zua
 */
public final class NewsMenuBar extends MenuBar implements CanPopulate {

    private static final long serialVersionUID = -1299703352057116843L;

    private final FlexUser user;
    private final NewsSourceServiceInterface sourcesService;

    // Main Menu (top level)
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
        this.initMenuItems();
    }

    protected void initMenuItems() {
        news = addItem("News", null, null);
        publishers = addItem("Publishers", null, null);
        categories = addItem("Categories", null, null);
        languages = addItem("Languages", null, null);
        countries = addItem("Countries", null, null);
        setSizeUndefined();
        setAutoOpen(true);
        setStyleName("news-menu-bar");
        addStyleName(ValoTheme.MENUBAR_BORDERLESS);
    }

    @Override
    public void populate() {
        populateNewsByTime();
        news.addSeparator();
        populateNewsByStatus();
        //news.addSeparator();
        //populateViews();
        populateNewsPublisher();
        populateNewsCategory();
        populateNewsLanguages();
        populateNewsCountries();
    }

    protected void populateNewsCategory() {
        Collection<String> cats = sourcesService.findCategories();
        cats.forEach(cat -> {
            categories.addItem(getCategoryCaption(cat), (selectedMenuItem) -> {
                updateBody(DataProviderType.CATEGORY, selectedMenuItem.getText());
            });
        });
    }

    protected void populateNewsPublisher() {
        Collection<String> names = sourcesService.findNames();
        names.forEach(name -> {
            publishers.addItem(name, (selectedMenuItem -> {
                updateBody(DataProviderType.PUBLISHER, selectedMenuItem.getText());
            }));
        });
    }

    protected void populateNewsLanguages() {
        Set<String> result = new TreeSet<>();
        Collection<String> locales = sourcesService.findLocales();
        locales.forEach(localeString -> {
            if (localeString != null && !localeString.isEmpty()) {
                result.add(MyDateUtils.getLanguageNameFromPattern(localeString));
            }
        });
        result.forEach(lang -> {
            languages.addItem(lang, (selectedMenuItem) -> {
                updateBody(DataProviderType.LANGUAGES, selectedMenuItem.getText());
            });
        });
    }

    protected void populateNewsCountries() {
        Set<String> result = new TreeSet<>();
        Collection<String> locales = sourcesService.findLocales();
        locales.forEach(localeString -> {
            result.add(MyDateUtils.getCountryNameFromPattern(localeString));
        });
        result.forEach(country -> {
            countries.addItem(country, (selectedMenuItem) -> {
                updateBody(DataProviderType.COUNTRIES, selectedMenuItem.getText());
            });
        });
    }

    private void populateViews() {
        NewsBody body = ((NewsView)getUI().getContent()).getBody();
        MasterDetailView masterDetail = body.getMasterDetail();
        full = news.addItem("Full", (selectedItem) -> {masterDetail.full();});
        imagesOnly = news.addItem("Images Only", (selectedItem) -> {masterDetail.imagesOnly();});
        titlesOnly = news.addItem("Titles Only", (selectedItem) -> { masterDetail.titlesOnly();});
    }

    private void populateNewsByTime() {
        latest = news.addItem("Latest", VaadinIcons.ARROW_CIRCLE_DOWN, (selectdMenuItem) -> {
            updateBody(DataProviderType.LATEST, null);
        });
        oldest = news.addItem("Oldest", VaadinIcons.ARROW_CIRCLE_UP, (selectedMenuItem) -> {
            updateBody(DataProviderType.OLDEST, null);
        });
    }

    private void populateNewsByStatus() {
        if(user != null) {
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
    }

    @Override
    public NewsUI getUI() {
        return (NewsUI) super.getUI();
    }

    private void updateBody(DataProviderType dataType, String value) {
        NewsBody body = ((NewsView) ((NewsUI)getUI()).getContent()).getBody();
        if (body != null) {
            body.populate(dataType, value);
        } else {
            Notification.show("Body Not Found");
        }
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
}
