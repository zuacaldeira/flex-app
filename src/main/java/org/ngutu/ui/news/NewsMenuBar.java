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
import org.ngutu.ui.auth0.NgutuAuthAPI;
import org.ngutu.ui.viewproviders.FlexViews;
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
    private MenuItem top;
    private MenuItem home;
    private MenuItem login;
    private MenuItem logout;

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
        setSizeUndefined();
        setAutoOpen(true);
        setStyleName("news-menu-bar");
        addStyleName(ValoTheme.MENUBAR_BORDERLESS);
    }

    protected void initMenuItems() {
        home = addItem("", VaadinIcons.HOME, (item -> {
            getUI().getNavigator().navigateTo(FlexViews.WELCOME);
        }));
        top = addItem("", VaadinIcons.GRID_SMALL, null);
        top.setStyleName("menu-bar-top");
        news = top.addItem("Articles", null, item -> {
            getUI().getNavigator().navigateTo(FlexViews.NEWS + "/articles");
        });
        publishers = top.addItem("Publishers", null, null);
        categories = top.addItem("Categories", null, null);
        languages = top.addItem("Languages", null, null);
        countries = top.addItem("Countries", null, null);
        if (user == null) {
            login = addItem("", VaadinIcons.SIGN_IN, (selectedItem) -> {
                NgutuAuthAPI authAPI = new NgutuAuthAPI(getUI().getNavigator().getState());
                authAPI.authorize();
            });
            login.setDescription("LOGIN");
        }
        if (user != null) {
            logout = addItem("", VaadinIcons.SIGN_OUT, (selectedItem) -> {
                getUI().getSession().setAttribute("user", null);
                getUI().getNavigator().navigateTo(FlexViews.WELCOME);
            });
            logout.setDescription("LOGOUT");
        }
        populate();
    }

    protected void populateNewsCategory() {
        sourcesService.findCategories().subscribe(cat -> {
            categories.addItem(getCategoryCaption(cat), (selectedMenuItem) -> {
                getUI().getNavigator().navigateTo(FlexViews.NEWS + "/articles/" + cat);
                updateBody(DataProviderType.CATEGORY, selectedMenuItem.getText());
            });
        });
    }

    protected void populateNewsPublisher() {
        sourcesService.findNames().subscribe(name -> {
            publishers.addItem(name, (selectedMenuItem -> {
                getUI().getNavigator().navigateTo(FlexViews.NEWS + "/articles/" + name);
                updateBody(DataProviderType.PUBLISHER, selectedMenuItem.getText());
            }));
        });
    }

    protected void populateNewsLanguages() {
        sourcesService.findLocales().subscribe(localeString -> {
            if (localeString != null && !localeString.isEmpty()) {
                String lang = MyDateUtils.getLanguageNameFromPattern(localeString);
                languages.addItem(lang, (selectedMenuItem) -> {
                    getUI().getNavigator().navigateTo(FlexViews.NEWS + "/articles/" + lang);
                    updateBody(DataProviderType.LANGUAGES, selectedMenuItem.getText());
                });
            }
        });
    }

    protected void populateNewsCountries() {
        sourcesService.findLocales().subscribe(localeString -> {
            if (localeString != null && !localeString.isEmpty()) {
                String country = MyDateUtils.getCountryNameFromPattern(localeString);
                countries.addItem(country, (selectedMenuItem) -> {
                    getUI().getNavigator().navigateTo(FlexViews.NEWS + "/articles/" + country);
                    updateBody(DataProviderType.LANGUAGES, selectedMenuItem.getText());
                });
            }
        });
    }

    private void populateViews() {
        full = news.addItem("Full", (selectedItem) -> {
            getUI().getNavigator().navigateTo(FlexViews.NEWS + "/articles/full");
            NewsBody body = ((NewsView) getUI().getContent()).getBody();
            MasterDetailView masterDetail = body.getMasterDetail();
            masterDetail.full();
        });
        imagesOnly = news.addItem("Images Only", (selectedItem) -> {
            getUI().getNavigator().navigateTo(FlexViews.NEWS + "/articles/images");
            NewsBody body = ((NewsView) getUI().getContent()).getBody();
            MasterDetailView masterDetail = body.getMasterDetail();
            masterDetail.imagesOnly();
        });
        titlesOnly = news.addItem("Titles Only", (selectedItem) -> {
            getUI().getNavigator().navigateTo(FlexViews.NEWS + "/articles/titles");
            NewsBody body = ((NewsView) getUI().getContent()).getBody();
            MasterDetailView masterDetail = body.getMasterDetail();
            masterDetail.titlesOnly();
        });
    }

    private void populateNewsByTime() {
        latest = news.addItem("Latest", (selectedItem) -> {
            getUI().getNavigator().navigateTo(FlexViews.NEWS + "/articles/latest");
            updateBody(DataProviderType.LATEST, null);
        });
        oldest = news.addItem("Oldest", (selectedItem) -> {
            getUI().getNavigator().navigateTo(FlexViews.NEWS + "/articles/oldest");
            updateBody(DataProviderType.OLDEST, null);
        });
    }

    private void populateNewsByStatus() {
        if (user != null) {
            news.addSeparator();
            read = news.addItem("Read", VaadinIcons.EYE_SLASH, (selectedMenuItem) -> {
                getUI().getNavigator().navigateTo(FlexViews.NEWS + "/articles/read");
                updateBody(DataProviderType.READ, null);
            });
            favorite = news.addItem("Favorite", VaadinIcons.STAR, (selectedMenuItem) -> {
                getUI().getNavigator().navigateTo(FlexViews.NEWS + "/articles/favorite");
                updateBody(DataProviderType.FAVORITE, null);
            });
            fake = news.addItem("Fake", VaadinIcons.EXCLAMATION_CIRCLE, (selectMenuItem) -> {
                getUI().getNavigator().navigateTo(FlexViews.NEWS + "/articles/fake");
                updateBody(DataProviderType.FAKE, null);
            });
        }
    }

    private void updateBody(DataProviderType dataType, String value) {
        NewsBody body = ((NewsView) getUI().getNavigator().getCurrentView()).getBody();
        body.populate(dataType, value);
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

    private void populate() {
        populateNewsByTime();
        populateNewsByStatus();
        populateNewsPublisher();
        populateNewsCategory();
        populateNewsLanguages();
        populateNewsCountries();
    }

}
