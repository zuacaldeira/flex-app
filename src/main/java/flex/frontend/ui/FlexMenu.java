/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author zua
 */
public class FlexMenu extends HorizontalLayout {

    private Label logo;
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

    private void initLoginButton() {
        loginButton = new FlexButton("Login", VaadinIcons.USER);
    }

    private void initSearchButton() {
        searchButton = new FlexButton("Search", VaadinIcons.SEARCH);
    }

    private void initMapButton() {
        mapButton = new FlexButton("Views", VaadinIcons.VIEWPORT);
    }

    private void initMenuButton() {
        menuButton = new FlexButton("Menu", VaadinIcons.MENU);
    }
    

}
