/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.menu;

import ui.view.body.FlexBody;
import utils.UIUtils;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import db.FlexUser;
import java.util.Set;
import data.DataProviderType;
import java.util.Collection;
import java.util.TreeSet;
import services.NewsSourceServiceInterface;
import ui.SecuredUI;
import utils.MyDateUtils;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public final class FlexMenuBar extends MenuBar implements CanPopulate {

    private final FlexUser user;
    private final NewsSourceServiceInterface sourcesService;

    // Main Menu (top level)
    private MenuItem home;
    private MenuItem news;
    private MenuItem categories;
    private MenuItem publishers;
    private MenuItem languages;
    private MenuItem countries;
    private MenuItem logout;
    private MenuItem search;

    private MenuBar.Command command;
    private MenuItem read;
    private MenuItem favorite;
    private MenuItem fake;
    private MenuItem oldest;
    private MenuItem latest;
    private MenuItem full;
    private MenuItem imagesOnly;
    private MenuItem titlesOnly;

    public FlexMenuBar(FlexUser user) {
        this.user = user;
        sourcesService = ServiceLocator.getInstance().findSourcesService();
        this.initMenuItems();
        this.setMoreMenuItem(home);
    }

    protected void initMenuItems() {
        home = addItem("", VaadinIcons.HOME, (selectedItem) -> {
            if (Page.getCurrent() != null) {
                Page.getCurrent().setLocation("/flex-app");
            }
        });
        news = addItem("News", null, null);
        publishers = addItem("Publishers", null, null);
        categories = addItem("Categories", null, null);
        languages = addItem("Languages", null, null);
        countries = addItem("Countries", null, null);
        search = addItem("", VaadinIcons.SEARCH, () -> {
            if (UI.getCurrent() != null) {
                UI.getCurrent().addWindow(new SearchWindow(user));
            }
        });
        logout = addItem("", VaadinIcons.SIGN_OUT, () -> {
            if (UI.getCurrent() != null) {
                Notification.show("LOGOUT");
                getSession().setAttribute("user", null);
                Page.getCurrent().setLocation("/flex-app");
            }
        });

        setSizeUndefined();
        setAutoOpen(true);
        setStyleName("flex-menu-bar");
        addStyleName(ValoTheme.MENUBAR_BORDERLESS);
    }

    @Override
    public void populate() {
        populateNewsByTime();
        news.addSeparator();
        populateNewsByStatus();
        news.addSeparator();
        populateViews();
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
        full = news.addItem("Full", (selectedMenuItem) -> {
            updateBody(DataProviderType.FULL, null);
        });
        imagesOnly = news.addItem("Images Only", (selectedMenuItem) -> {
            updateBody(DataProviderType.IMAGES_ONLY, null);
        });
        titlesOnly = news.addItem("Titles Only", (selectedMenuItem) -> {
            updateBody(DataProviderType.TITLES_ONLY, null);
        });
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

    @Override
    public SecuredUI getUI() {
        return (SecuredUI) super.getUI();
    }

    private void updateBody(MenuItem selectedItem) {
        FlexBody body = UIUtils.getInstance().getBody(this);
        if (body != null) {
            body.initBodyUpdaterThread(getDataProviderType(selectedItem), selectedItem.getText());
        } else {
            Notification.show("Body Not Found");
        }
    }

    private void updateBody(DataProviderType dataType, String value) {
        FlexBody body = UIUtils.getInstance().getBody(this);
        if (body != null) {
            body.initBodyUpdaterThread(dataType, value);
        } else {
            Notification.show("Body Not Found");
        }
    }

    public DataProviderType getDataProviderType(MenuItem selectedItem) {
        if (selectedItem.getParent() == null || selectedItem.getParent() == news) {
            return getTopDataProviderType(selectedItem);
        } else {
            return getTopDataProviderType(selectedItem.getParent());
        }
    }

    private String getCategoryCaption(String cat) {
        char c = cat.charAt(0);
        return cat.replaceFirst(
                String.valueOf(c),
                String.valueOf(Character.toUpperCase(c)))
                .replace("-", " ");
    }

    public MenuItem getHome() {
        return home;
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

    public MenuItem getLogout() {
        return logout;
    }

    public MenuItem getSearch() {
        return search;
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

    public Command getCommand() {
        return command;
    }

    public FlexUser getUser() {
        return user;
    }

    private DataProviderType getTopDataProviderType(MenuItem selectedItem) {
        if (selectedItem.getText() == null || selectedItem.getText().isEmpty()) {
            return getIconicDataProviderType(selectedItem);
        }
        switch (selectedItem.getText()) {
            case "Latest":
                return DataProviderType.LATEST;
            case "Oldest":
                return DataProviderType.OLDEST;
            case "Read":
                return DataProviderType.READ;
            case "Favorite":
                return DataProviderType.FAVORITE;
            case "Fake":
                return DataProviderType.FAKE;
            case "Full":
                return DataProviderType.FULL;
            case "Images Only":
                return DataProviderType.IMAGES_ONLY;
            case "Titles Only":
                return DataProviderType.TITLES_ONLY;
            case "Categories":
                return DataProviderType.CATEGORY;
            case "Publishers":
                return DataProviderType.PUBLISHER;
            case "Languages":
                return DataProviderType.LANGUAGES;
            case "Countries":
                return DataProviderType.COUNTRIES;
            default:
                return DataProviderType.LATEST;
        }
    }

    private DataProviderType getIconicDataProviderType(MenuItem selectedItem) {
        if (selectedItem.getIcon().equals(VaadinIcons.SEARCH)) {
            return DataProviderType.SEARCH;
        }
        if (selectedItem.getIcon().equals(VaadinIcons.SIGN_OUT)) {
            return DataProviderType.LOGOUT;
        }
        return DataProviderType.HOME;
    }

}
