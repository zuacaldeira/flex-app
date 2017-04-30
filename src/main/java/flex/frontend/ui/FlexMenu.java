/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;
import flex.backend.db.ApiSource;
import org.utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class FlexMenu extends HorizontalLayout {

    private Label logo;
    private FlexComboBox<ApiSource> sourcesComboBox;
    private FlexComboBox<String> categoryComboBox;
    private FlexComboBox<String> languageComboBox;
    private final HorizontalLayout filters;
    private FlexButton loginButton;
    private FlexButton searchButton;
    private FlexButton mapButton;
    private FlexButton menuButton;
    
    
    public FlexMenu() {
        setWidth("100%");
        setHeight("2cm");
        setStyleName("flex-menu");
        initLogo();
        initLoginButton();
        initSearchButton();
        initMapButton();
        initMenuButton();
        
        initSourcesComboBox();
        initCategoryComboBox();
        initLanguageComboBox();
        
        filters = new HorizontalLayout(sourcesComboBox, categoryComboBox, languageComboBox);
        HorizontalLayout actions = new HorizontalLayout(loginButton, mapButton, searchButton, menuButton);
        actions.setMargin(new MarginInfo(false, true, false, false));
        
        addComponents(logo, actions);
        setComponentAlignment(logo, Alignment.MIDDLE_CENTER);        
        setComponentAlignment(actions, Alignment.MIDDLE_RIGHT);        
    }

    public Label getLogo() {
        return logo;
    }

    public void setLogo(Label logo) {
        this.logo = logo;
    }

    
    private void initLogo() {
        logo = new Label("The World News!");
        logo.setSizeUndefined();
        logo.setStyleName(ValoTheme.LABEL_BOLD + ", " + ValoTheme.LABEL_HUGE);
        logo.addStyleName("flex-logo");
    }
    private void initSourcesComboBox() {
        sourcesComboBox = new FlexComboBox<>("Choose news source");
        sourcesComboBox.setDataProvider(
            new ListDataProvider<>(
                ServiceLocator.findNewsLoaderService().loadSources().getSources()
            )
        );
    }

    private void initCategoryComboBox() {
        categoryComboBox = new FlexComboBox<>("Choose news category");
        categoryComboBox.setDataProvider(
            new ListDataProvider<>(
                ServiceLocator.findNewsLoaderService().loadCategories())
        );
        categoryComboBox.setStyleName(ValoTheme.COMBOBOX_SMALL);
    }
    

    private void initLanguageComboBox() {
        languageComboBox = new FlexComboBox<>("Choose news language");
        languageComboBox.setDataProvider(new ListDataProvider<>(
            ServiceLocator.findNewsLoaderService().loadLanguages()));
    }

    public FlexComboBox<ApiSource> getSourcesComboBox() {
        return sourcesComboBox;
    }

    public FlexComboBox<String> getCategoryComboBox() {
        return categoryComboBox;
    }

    public FlexComboBox<String> getLanguageComboBox() {
        return languageComboBox;
    }

    public HorizontalLayout getFilters() {
        return filters;
    }

    private void initLoginButton() {
        loginButton = new FlexButton("Login", FontAwesome.USER);
    }

    private void initSearchButton() {
        searchButton = new FlexButton("Search", FontAwesome.SEARCH);
    }

    private void initMapButton() {
        mapButton = new FlexButton("Views", FontAwesome.EYE);
    }

    private void initMenuButton() {
        menuButton = new FlexButton("Menu", FontAwesome.NAVICON);
    }
    

}
