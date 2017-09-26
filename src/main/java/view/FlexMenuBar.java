/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import utils.UIUtils;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;
import db.FlexUser;
import java.util.Locale;
import java.util.Set;
import data.DataProviderType;
import java.util.Collection;
import java.util.TreeSet;
import ui.SecuredUI;
import utils.MyDateUtils;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class FlexMenuBar extends MenuBar {

    // Main Menu (top level)
    private MenuItem news;
    private MenuItem categories;
    private MenuItem publishers;
    private MenuItem status;
    private MenuItem languages;
    private MenuItem countries;
    private MenuItem views;

    private MenuBar.Command command;
    private final FlexUser user;
    
    public FlexMenuBar(FlexUser user) {
        this.user = user;
        initMenuBar();
    }
    
    private void initMenuBar() {
        setSizeUndefined();
        setAutoOpen(true);
        setStyleName("flex-menu-bar");
        addStyleName(ValoTheme.MENUBAR_BORDERLESS);
        initCommand();
        initMenuNews();
    }
    
    private void initCommand() {
        command = (MenuItem selectedItem) -> { 
            selectedItem.setStyleName("selected");
            updateBody(selectedItem);
        };
    }
    
    private void initMenuNews() {
        news = addItem("NEWS", null);

        publishers = addItem("PUBLISHERS", null, null);
        updateNewsPublisher();

        categories = addItem("CATEGORIES", null, null);
        updateNewsCategory();

        status = addItem("STATUS", null, null);
        updateNewsByStatus();
        
        languages = addItem("LANGUAGES", null, null);
        updateNewsLanguages();

        countries = addItem("COUNTRIES", null, null);
        updateNewsCountries();

        views = addItem("VIEWS", null, null);
        updateViews();

        updateNewsByTime();
    }
        
    protected void updateNewsCategory() {
        Collection<String> cats = ServiceLocator.getInstance().findSourcesService().findCategories();
        cats.forEach(cat -> {
            categories.addItem(getCategoryCaption(cat), command);
        });
    }

    protected void updateNewsPublisher() {
        Collection<String> names = ServiceLocator.getInstance().findSourcesService().findNames();
        names.forEach(name -> {
            publishers.addItem(name, command);
        });
    }

    protected void updateNewsLanguages() {
        Set<String> result = new TreeSet<>();

        Collection<String> locales = ServiceLocator.getInstance().findSourcesService().findLocales();        
        locales.forEach(localeString -> {
            if(localeString != null && !localeString.isEmpty()) {
                result.add(MyDateUtils.getLanguageNameFromPattern(localeString));
            }
        });
        
        result.forEach(lang -> {
            languages.addItem(lang, command);
        });
    }

    protected void updateNewsCountries() {
        Set<String> result = new TreeSet<>();

        Collection<String> locales = ServiceLocator.getInstance().findSourcesService().findLocales();
        locales.forEach(localeString -> {
            result.add(MyDateUtils.getCountryNameFromPattern(localeString));
        });
        
        result.forEach(country -> {
            countries.addItem(country, command);
        });
    }
    
    private void updateViews() {
        views.addItem("Full", command);
        views.addItem("Images Only", command);
        views.addItem("Titles Only", command);
    }


    private void updateNewsByTime() {
        news.addItem("Latest", VaadinIcons.ARROW_CIRCLE_DOWN, command);
        news.addItem("Oldest", VaadinIcons.ARROW_CIRCLE_UP, command);
    }
    
    private void updateNewsByStatus() {
        status.addItem("Read", VaadinIcons.EYE_SLASH, command);
        status.addItem("Favorite", VaadinIcons.STAR, command);
        status.addItem("Fake", VaadinIcons.EXCLAMATION_CIRCLE, command);
    }

    @Override
    public SecuredUI getUI() {
        return (SecuredUI) super.getUI();
    }

    private void updateBody(MenuItem selectedItem) {
        FlexBody body = UIUtils.getInstance().getBody(this);
        if(body != null) {
            body.updateData(getDataProviderType(selectedItem), selectedItem.getText());
        } else {
            Notification.show("Body Not Found");
        }
    }
    
    public DataProviderType getDataProviderType(MenuItem selectedItem) {
        if(selectedItem.getParent() != null && selectedItem.getParent().getText().equals("CATEGORIES")) {
            return DataProviderType.CATEGORY;
        }
        else if(selectedItem.getParent() != null && selectedItem.getParent().getText().equals("PUBLISHERS")) {
            return DataProviderType.PUBLISHER;
        }
        else if(selectedItem.getParent() != null && selectedItem.getParent().getText().equals("LANGUAGES")) {
            return DataProviderType.LANGUAGES;
        }
        else if(selectedItem.getParent() != null && selectedItem.getParent().getText().equals("COUNTRIES")) {
            return DataProviderType.COUNTRIES;
        }
        else if(selectedItem.getText().equals("Latest")) {
            return DataProviderType.LATEST;
        }
        else if(selectedItem.getText().equals("Oldest")) {
            return DataProviderType.OLDEST;
        }
        else if(selectedItem.getText().equals("Read")) {
            return DataProviderType.READ;
        }
        else if(selectedItem.getText().equals("Favorite")) {
            return DataProviderType.FAVORITE;
        }
        else if(selectedItem.getText().equals("Fake")) {
            return DataProviderType.FAKE;
        }
        else if(selectedItem.getText().equals("Full")) {
            return DataProviderType.FULL;
        }
        else if(selectedItem.getText().equals("Images Only")) {
            return DataProviderType.IMAGES_ONLY;
        }
        else if(selectedItem.getText().equals("Titles Only")) {
            return DataProviderType.TITLES_ONLY;
        }
        return null;
    }

    private String getLanguageCaption(String lang) {
        Locale locale = Locale.forLanguageTag(lang);
        return lang + " - " + locale.getDisplayLanguage();
    }
    
    private String getCountryCaption(String localeString) {
        Locale locale = Locale.forLanguageTag(MyDateUtils.extractLanguage(localeString));
        return locale.getCountry() + " - " + locale.getDisplayCountry();
    }

    private String getCategoryCaption(String cat) {
        char c = cat.charAt(0);
        return cat.replaceFirst(
                        String.valueOf(c), 
                        String.valueOf(Character.toUpperCase(c)))
                    .replace("-", " ");
    }

    
}


