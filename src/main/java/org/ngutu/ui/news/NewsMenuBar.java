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
import data.DataProviderType;
import db.auth.FlexUser;
import db.news.Tag;
import java.util.TreeSet;
import org.ngutu.ui.viewproviders.FlexViews;
import backend.utils.MyDateUtils;
import com.vaadin.ui.themes.ValoTheme;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public final class NewsMenuBar extends MenuBar {

    private static final long serialVersionUID = -1299703352057116843L;

    // Main Menu (top level)
    private MenuItem top;

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
        setStyleName(ValoTheme.MENUBAR_BORDERLESS);
        addStyleName("flex-menu-bar");
    }

    protected void initMenuItems() {
        //top = addItem("Menu", VaadinIcons.MENU, null);
        //top.setStyleName("menu-bar-top");
        news = addItem("Articles", null, null);
        publishers = news.addItem("Publishers", null, null);
        categories = news.addItem("Categories", null, null);
        languages = news.addItem("Languages", null, null);
        countries = news.addItem("Countries", null, null);
        populate();
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
            categories.addItem(tag, new CategoryCommand());
        });
    }

    protected void populateNewsPublisher() {
        TreeSet<String> ps = new TreeSet<>();
        Iterable<String> sourceNames = ServiceLocator.getInstance().findSourcesService().findNames();
        sourceNames.forEach(name -> {
            if (name != null) {
                ps.add(name);
            }
        });

        ps.forEach(publisher -> {
            publishers.addItem(publisher, new PublisherCommand());
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
            languages.addItem(lang, new LanguageCommand());
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
            countries.addItem(country, new CountryCommand());
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
        latest = news.addItem("Latest", new LatestCommand());
        oldest = news.addItem("Oldest", new OldestCommand());
    }

    private void populateNewsByStatus() {
        if (getUser() != null) {
            news.addSeparator();
            read = news.addItem("Read", VaadinIcons.EYE_SLASH, new ReadCommand());
            favorite = news.addItem("Favorite", VaadinIcons.STAR, new FavoriteCommand());
            fake = news.addItem("Fake", VaadinIcons.EXCLAMATION_CIRCLE, new FakeCommand());
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

    private static class PublisherCommand implements Command {

        private static final long serialVersionUID = -6320032619121769747L;

        public PublisherCommand() {
        }

        @Override
        public void menuSelected(MenuItem selectedItem) {
            String value = selectedItem.getText().trim();
            value = value.replace(' ', '-');
            UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS + "/" + DataProviderType.PUBLISHER + "/" + value);
        }

    }

    private static class CategoryCommand implements Command {

        private static final long serialVersionUID = -6713270025833148214L;

        public CategoryCommand() {
        }

        @Override
        public void menuSelected(MenuItem selectedItem) {
            String value = selectedItem.getText().trim();
            value = value.replace(' ', '-');
            UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS + "/" + DataProviderType.CATEGORY + "/" + value);
        }

    }

    private static class LanguageCommand implements Command {

        private static final long serialVersionUID = -4583498482443517774L;

        public LanguageCommand() {
        }

        @Override
        public void menuSelected(MenuItem selectedItem) {
            String value = selectedItem.getText().trim();
            value = value.replace(' ', '-');
            UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS + "/" + DataProviderType.LANGUAGES + "/" + value);
        }

    }

    private static class CountryCommand implements Command {

        private static final long serialVersionUID = -4146615261776133329L;

        public CountryCommand() {
        }

        @Override
        public void menuSelected(MenuItem selectedItem) {
            String value = selectedItem.getText().trim();
            value = value.replace(' ', '-');
            UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS + "/" + DataProviderType.COUNTRIES + "/" + value);
        }
    }

    private static class LatestCommand implements Command {

        private static final long serialVersionUID = -2359955325027587125L;

        public LatestCommand() {
        }

        @Override
        public void menuSelected(MenuItem selectedItem) {
            UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS + "/" + DataProviderType.LATEST);
        }

    }

    private static class OldestCommand implements Command {

        private static final long serialVersionUID = 7376938084774131516L;

        public OldestCommand() {
        }

        @Override
        public void menuSelected(MenuItem selectedItem) {
            UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS + "/" + DataProviderType.OLDEST);
        }

    }

    private static class ReadCommand implements Command {

        private static final long serialVersionUID = 3653566244729923141L;

        public ReadCommand() {
        }

        @Override
        public void menuSelected(MenuItem selectedItem) {
            UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS + "/" + DataProviderType.READ);
        }

    }

    private static class FavoriteCommand implements Command {

        private static final long serialVersionUID = 198466133662186366L;

        public FavoriteCommand() {
        }

        @Override
        public void menuSelected(MenuItem selectedItem) {
            UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS + "/" + DataProviderType.FAVORITE);
        }

    }

    private static class FakeCommand implements Command {

        private static final long serialVersionUID = 8502301616111195999L;

        public FakeCommand() {
        }

        @Override
        public void menuSelected(MenuItem selectedItem) {
            UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS + "/" + DataProviderType.FAKE);
        }

    }

}
