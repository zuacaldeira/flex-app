/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import flex.frontend.ui.news.ArticlesInfoView;
import flex.frontend.ui.news.AuthorsInfoView;
import flex.frontend.ui.news.NewsUI;
import flex.frontend.ui.news.NewsView;
import flex.frontend.ui.news.SourcesInfoView;

/**
 *
 * @author zua
 */
public class NewsMenu extends FlexMenu {

    private FlexButton homeButton;
    private FlexButton searchButton;
    private FlexButton sourcesButton;
    private FlexButton menuButton;
    private FlexButton authorsButton;
    private FlexButton articlesButton;
    
    
    public NewsMenu() {
        setSizeFull();
        setSpacing(false);
        setStyleName("flex-menu");
        initHomeButton();
        initSearchButton();
        initSourcesButton();
        initAuthorsButton();
        initArticlesButton();
        initMenuButton();

        addComponents(homeButton, sourcesButton, authorsButton, articlesButton, searchButton, menuButton);
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
    }

    private void initHomeButton() {
        homeButton = new FlexButton("Home", VaadinIcons.HOME);
        homeButton.setSizeUndefined();
        homeButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Page.getCurrent().setLocation("/flex-app");
            }
        });
    }

    private void initSearchButton() {
        searchButton = new FlexButton("Search", VaadinIcons.SEARCH);
        searchButton.setSizeUndefined();
    }

    private void initSourcesButton() {
        sourcesButton = new FlexButton("Sources", VaadinIcons.BUILDING);
        sourcesButton.setSizeUndefined();
        sourcesButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Notification.show("Sources clicked");
                getNewsView().setBody(new SourcesInfoView());
            }
        });
    }

    private void initAuthorsButton() {
        authorsButton = new FlexButton("Authors", VaadinIcons.USER_CARD);
        authorsButton.setSizeUndefined();
        authorsButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Notification.show("Authors clicked");
                getNewsView().setBody(new AuthorsInfoView());
            }
        });
    }

    private void initArticlesButton() {
        articlesButton = new FlexButton("Articles", VaadinIcons.NOTEBOOK);
        articlesButton.setSizeUndefined();
        articlesButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Notification.show("Articles clicked");
                getNewsView().setBody(new ArticlesInfoView());
            }
        });
    }
    
    private void initMenuButton() {
        menuButton = new FlexButton("Menu", VaadinIcons.MENU);
        menuButton.setSizeUndefined();
    }

    private NewsView getNewsView() {
        return ((NewsUI) UI.getCurrent()).getNewsView();
    }

}
