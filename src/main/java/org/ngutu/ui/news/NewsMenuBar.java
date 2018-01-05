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
import java.util.TreeSet;
import org.ngutu.ui.viewproviders.FlexViews;
import backend.utils.MyDateUtils;
import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.LanguageCode;
import com.vaadin.ui.themes.ValoTheme;
import db.news.NewsSource;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.Collection;
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
    private Collection<NewsSource> sources;
    private TreeSet<String> tags;
    private TreeSet<String> publisherLanguages;
    private TreeSet<String> publisherNames;
    private TreeSet<String> publisherCountries;

    public NewsMenuBar() {
        this.loadData();
        this.initMenuItems();
        setSizeUndefined();
        setAutoOpen(true);
        setStyleName(ValoTheme.MENUBAR_BORDERLESS);
        setStyleName("flex-menu-bar");
    }

    private void loadData() {
        publisherNames = new TreeSet<>();
        publisherLanguages = new TreeSet<>();
        publisherCountries = new TreeSet<>();
        tags = new TreeSet<>();
        Observable<NewsSource> observable = Observable.fromIterable(ServiceLocator.getInstance().findSourcesService().findAllSources());
        Disposable disposable = observable.subscribe(onNext -> {
            publisherNames.add(onNext.getName());
            publisherLanguages.add(onNext.getLanguage());
            publisherCountries.add(onNext.getCountry());
            tags.add(getCategoryCaption(onNext.getCategory().getTag()));
        }, onError -> {
            onError.printStackTrace();
        });
    }
    
    protected void initMenuItems() {
        top = addItem("Menu", VaadinIcons.MENU, null);
        news = top.addItem("Articles", null, null);
        publishers = top.addItem("Publishers", null, null);
        categories = top.addItem("Categories", null, null);
        languages = top.addItem("Languages", null, null);
        countries = top.addItem("Countries", null, null);
        populate();
    }

    private FlexUser getUser() {
        if (UI.getCurrent() != null) {
            return (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        }
        return null;
    }

    protected void populateNewsCategory() {
        tags.forEach(tag -> {
            categories.addItem(tag, new CategoryCommand());
        });
    }

    protected void populateNewsPublisher() {
        publisherNames.forEach(name -> {
            publishers.addItem(name, new PublisherCommand());
        });
    }

    protected void populateNewsLanguages() {
        publisherLanguages.forEach(lang -> {
            if(LanguageCode.getByCode(lang) != null) {
                languages.addItem(LanguageCode.getByCode(lang).getName(), new LanguageCommand());
            }
        });
    }

    protected void populateNewsCountries() {
        publisherCountries.forEach(country -> {
            if(CountryCode.getByCode(country) != null) {
                countries.addItem(CountryCode.getByCode(country).getName(), new CountryCommand());
            }
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
            UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS + "/" + DataProviderType.PUBLISHER.name().toLowerCase() + "/" + value);
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
            UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS + "/" + DataProviderType.CATEGORY.name().toLowerCase() + "/" + value);
        }

        private String getCategoryFromCaption(String caption) {
            char c = caption.charAt(0);
            return caption.replaceFirst(
                    String.valueOf(c),
                    String.valueOf(Character.toLowerCase(c)))
                    .replace(" ", "-");
        }

    }

    private static class LanguageCommand implements Command {

        private static final long serialVersionUID = -4583498482443517774L;

        public LanguageCommand() {
        }

        @Override
        public void menuSelected(MenuItem selectedItem) {
            String value = selectedItem.getText().trim();
            if (MyDateUtils.getLanguageCode(value) != null) {
                value = value.replace(' ', '-');
                UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS + "/" + DataProviderType.LANGUAGES.name().toLowerCase() + "/" + value);
            }
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
            UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS + "/" + DataProviderType.COUNTRIES.name().toLowerCase() + "/" + value);
        }
    }

    private static class LatestCommand implements Command {

        private static final long serialVersionUID = -2359955325027587125L;

        public LatestCommand() {
        }

        @Override
        public void menuSelected(MenuItem selectedItem) {
            UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS + "/" + DataProviderType.LATEST.name().toLowerCase());
        }

    }

    private static class OldestCommand implements Command {

        private static final long serialVersionUID = 7376938084774131516L;

        public OldestCommand() {
        }

        @Override
        public void menuSelected(MenuItem selectedItem) {
            UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS + "/" + DataProviderType.OLDEST.name().toLowerCase());
        }

    }

    private static class ReadCommand implements Command {

        private static final long serialVersionUID = 3653566244729923141L;

        public ReadCommand() {
        }

        @Override
        public void menuSelected(MenuItem selectedItem) {
            UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS + "/" + DataProviderType.READ.name().toLowerCase());
        }

    }

    private static class FavoriteCommand implements Command {

        private static final long serialVersionUID = 198466133662186366L;

        public FavoriteCommand() {
        }

        @Override
        public void menuSelected(MenuItem selectedItem) {
            UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS + "/" + DataProviderType.FAVORITE.name().toLowerCase());
        }

    }

    private static class FakeCommand implements Command {

        private static final long serialVersionUID = 8502301616111195999L;

        public FakeCommand() {
        }

        @Override
        public void menuSelected(MenuItem selectedItem) {
            UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS + "/" + DataProviderType.FAKE.name().toLowerCase());
        }

    }

}
