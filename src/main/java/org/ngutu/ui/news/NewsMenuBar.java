/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import data.DataProviderType;
import db.auth.FlexUser;
import db.news.Tag;
import java.util.TreeSet;
import org.ngutu.ui.share.NgutuFacebookAPI;
import org.ngutu.ui.viewproviders.FlexViews;
import ui.NgutuUI;
import backend.utils.MyDateUtils;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public final class NewsMenuBar extends MenuBar {

    private static final long serialVersionUID = -1299703352057116843L;

    // Main Menu (top level)
    private MenuItem top;
    private MenuItem home;
    private MenuItem logInOut;

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

    public NewsMenuBar() {
        this.initMenuItems();
        setSizeUndefined();
        setAutoOpen(true);
        setStyleName("news-menu-bar");
        addStyleName(ValoTheme.MENUBAR_BORDERLESS);
    }

    protected void initMenuItems() {
        top = addItem("", VaadinIcons.GRID_SMALL, null);
        top.setStyleName("menu-bar-top");
        news = top.addItem("Articles", null, null);
        publishers = top.addItem("Publishers", null, null);
        categories = top.addItem("Categories", null, null);
        languages = top.addItem("Languages", null, null);
        countries = top.addItem("Countries", null, null);
        home = addItem("", VaadinIcons.HOME, (item -> {
            getUI().getNavigator().navigateTo(FlexViews.WELCOME);
        }));
        if (getUser() != null) {
            logInOut = addItem("Logout", VaadinIcons.POWER_OFF, (item -> {
                getUI().getSession().setAttribute("user", null);
                getUI().getNavigator().navigateTo(FlexViews.NEWS);
            }));
        } else {
            logInOut = addItem("Login", VaadinIcons.FACEBOOK, (item -> {
                if (UI.getCurrent() != null && ((NgutuUI) UI.getCurrent()).getFacebookAPI() != null) {
                    NgutuFacebookAPI authAPI = ((NgutuUI) UI.getCurrent()).getFacebookAPI();
                    authAPI.setFragment(UI.getCurrent().getNavigator().getState());
                    authAPI.authorize();
                }
            }));
        }
    }

    private FlexUser getUser() {
        if (UI.getCurrent() != null) {
            System.out.println("Found USER -> " + UI.getCurrent().getSession().getAttribute("user"));
            return (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        }
        return null;
    }

    protected void populateNewsCategory() {
        TreeSet<String> tags = new TreeSet<>();
        Iterable<Tag> observable = ServiceLocator.getInstance().findTagService().findAllTags();
        observable.forEach(cat -> {
            tags.add(cat.getTag());
        });

        tags.forEach(tag -> {
            categories.addItem(tag, (selectedMenuItem) -> {
                updateBody(DataProviderType.CATEGORY, selectedMenuItem.getText());
            });
        });
    }

    protected void populateNewsPublisher() {
        TreeSet<String> ps = new TreeSet<>();
        Iterable<String> sourceNames = ServiceLocator.getInstance().findSourcesService().findNames();
        sourceNames.forEach(name -> {
            if(name != null) {
                ps.add(name);
            }
        });

        ps.forEach(publisher -> {
            publishers.addItem(publisher, (selectedMenuItem) -> {
                updateBody(DataProviderType.PUBLISHER, selectedMenuItem.getText());
            });
        });
    }

    protected void populateNewsLanguages() {
        TreeSet<String> ls = new TreeSet<>();
        Iterable<String> sourceLocales = ServiceLocator.getInstance().findSourcesService().findLocales();
        sourceLocales.forEach(localeString -> {
            if (localeString != null && !localeString.isEmpty()) {
                String lang = MyDateUtils.getLanguageNameFromPattern(localeString);
                ls.add(lang);
            }
        });

        ls.forEach(lang -> {
            languages.addItem(lang, (selectedMenuItem) -> {
                updateBody(DataProviderType.LANGUAGES, selectedMenuItem.getText());
            });
        });
    }

    protected void populateNewsCountries() {
        TreeSet<String> cs = new TreeSet<>();
        Iterable<String> sourceLocales = ServiceLocator.getInstance().findSourcesService().findLocales();
        sourceLocales.forEach(localeString -> {
            if (localeString != null && !localeString.isEmpty()) {
                String country = MyDateUtils.getCountryNameFromPattern(localeString);
                cs.add(country);
            }
        });

        cs.forEach(country -> {
            countries.addItem(country, (selectedMenuItem) -> {
                updateBody(DataProviderType.COUNTRIES, selectedMenuItem.getText());
            });
        });
    }

    private void populateViews() {
        full = news.addItem("Full", (selectedItem) -> {
            NewsBody body = ((NewsView) getUI().getContent()).getBody();
            MasterDetailView masterDetail = body.getMasterDetail();
            masterDetail.full();
        });
        imagesOnly = news.addItem("Images Only", (selectedItem) -> {
            NewsBody body = ((NewsView) getUI().getContent()).getBody();
            MasterDetailView masterDetail = body.getMasterDetail();
            masterDetail.imagesOnly();
        });
        titlesOnly = news.addItem("Titles Only", (selectedItem) -> {
            NewsBody body = ((NewsView) getUI().getContent()).getBody();
            MasterDetailView masterDetail = body.getMasterDetail();
            masterDetail.titlesOnly();
        });
    }

    private void populateNewsByTime() {
        latest = news.addItem("Latest", (selectedItem) -> {
            updateBody(DataProviderType.LATEST, null);
        });
        oldest = news.addItem("Oldest", (selectedItem) -> {
            updateBody(DataProviderType.OLDEST, null);
        });
    }

    private void populateNewsByStatus() {
        if (getUser() != null) {
            news.addSeparator();
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

    void populate() {
        populateNewsByTime();
        populateNewsByStatus();
        populateNewsPublisher();
        populateNewsCategory();
        populateNewsLanguages();
        populateNewsCountries();
    }

}
