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
public class FlexMenuBar extends MenuBar implements CanPopulate {

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
    private final FlexUser user;
    
    private NewsSourceServiceInterface sourcesService;
    
    public FlexMenuBar(FlexUser user) {
        this.user = user;
        sourcesService = ServiceLocator.getInstance().findSourcesService();
        initMenuItems();
    }

    protected void initMenuItems() {
        command = (MenuItem selectedItem) -> {
            selectedItem.setStyleName("selected");
            updateBody(selectedItem);
        };

        home = addItem("", VaadinIcons.HOME, (selectedItem) -> {
            Page.getCurrent().setLocation("/flex-app");
        });
        news = addItem("News", null, null);
        publishers = addItem("Publishers", null, null);
        categories = addItem("Categories", null, null);
        languages = addItem("Languages", null, null);
        countries = addItem("Countries", null, null);
        search = addItem("", VaadinIcons.SEARCH, (selectedItem) -> {
            UI.getCurrent().addWindow(new SearchWindow(user));
        });
        logout = addItem("", VaadinIcons.SIGN_OUT, (selectedItem) -> {
            Notification.show("LOGOUT");
            getSession().setAttribute("user", null);
            Page.getCurrent().setLocation("/flex-app");
        });

        setSizeUndefined();
        setAutoOpen(true);
        setStyleName("flex-menu-bar");
        addStyleName(ValoTheme.MENUBAR_BORDERLESS);
    }

    @Override
    public void populate() {
        populateNewsByTime();
        populateNewsByStatus();
        populateViews();
        populateNewsPublisher();
        populateNewsCategory();
        populateNewsLanguages();
        populateNewsCountries();
    }

    protected void populateNewsCategory() {
        if(sourcesService != null) {
            Collection<String> cats = sourcesService.findCategories();
            cats.forEach(cat -> {
                categories.addItem(getCategoryCaption(cat), command);
            });
        }
    }

    protected void populateNewsPublisher() {
        if(sourcesService != null) {
            Collection<String> names = sourcesService.findNames();
            names.forEach(name -> {
                publishers.addItem(name, command);
            });
        }
    }

    protected void populateNewsLanguages() {
        if(sourcesService != null) {
            Set<String> result = new TreeSet<>();
            Collection<String> locales = sourcesService.findLocales();
            locales.forEach(localeString -> {
                if (localeString != null && !localeString.isEmpty()) {
                    result.add(MyDateUtils.getLanguageNameFromPattern(localeString));
                }
            });
            result.forEach(lang -> {
                languages.addItem(lang, command);
            });
        }

    }

    protected void populateNewsCountries() {
        if(sourcesService != null) {
            Set<String> result = new TreeSet<>();
            Collection<String> locales = sourcesService.findLocales();
            locales.forEach(localeString -> {
                result.add(MyDateUtils.getCountryNameFromPattern(localeString));
            });
            result.forEach(country -> {
                countries.addItem(country, command);
            });
        }
    }

    private void populateViews() {
        news.addItem("Full", command);
        news.addItem("Images Only", command);
        news.addItem("Titles Only", command);
    }

    private void populateNewsByTime() {
        news.addItem("Latest", VaadinIcons.ARROW_CIRCLE_DOWN, command);
        news.addItem("Oldest", VaadinIcons.ARROW_CIRCLE_UP, command);
    }

    private void populateNewsByStatus() {
        news.addItem("Read", VaadinIcons.EYE_SLASH, command);
        news.addItem("Favorite", VaadinIcons.STAR, command);
        news.addItem("Fake", VaadinIcons.EXCLAMATION_CIRCLE, command);
    }

    @Override
    public SecuredUI getUI() {
        return (SecuredUI) super.getUI();
    }

    private void updateBody(MenuItem selectedItem) {
        FlexBody body = UIUtils.getInstance().getBody(this);
        if (body != null) {
            body.updateData(getDataProviderType(selectedItem), selectedItem.getText());
        } else {
            Notification.show("Body Not Found");
        }
    }

    public DataProviderType getDataProviderType(MenuItem selectedItem) {
        if (selectedItem.getParent() != null && selectedItem.getParent().getText().equals("Categories")) {
            return DataProviderType.CATEGORY;
        } else if (selectedItem.getParent() != null && selectedItem.getParent().getText().equals("Publishers")) {
            return DataProviderType.PUBLISHER;
        } else if (selectedItem.getParent() != null && selectedItem.getParent().getText().equals("Languages")) {
            return DataProviderType.LANGUAGES;
        } else if (selectedItem.getParent() != null && selectedItem.getParent().getText().equals("Countries")) {
            return DataProviderType.COUNTRIES;
        } else if (selectedItem.getText().equals("Latest")) {
            return DataProviderType.LATEST;
        } else if (selectedItem.getText().equals("Oldest")) {
            return DataProviderType.OLDEST;
        } else if (selectedItem.getText().equals("Read")) {
            return DataProviderType.READ;
        } else if (selectedItem.getText().equals("Favorite")) {
            return DataProviderType.FAVORITE;
        } else if (selectedItem.getText().equals("Fake")) {
            return DataProviderType.FAKE;
        } else if (selectedItem.getText().equals("Full")) {
            return DataProviderType.FULL;
        } else if (selectedItem.getText().equals("Images Only")) {
            return DataProviderType.IMAGES_ONLY;
        } else if (selectedItem.getText().equals("Titles Only")) {
            return DataProviderType.TITLES_ONLY;
        }
        return null;
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

    public Command getCommand() {
        return command;
    }

    public FlexUser getUser() {
        return user;
    }

    
}
