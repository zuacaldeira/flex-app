/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import flex.frontend.ui.FlexButton;
import flex.frontend.ui.FlexMenu;
import flex.frontend.ui.news.article.ArticlesBody;
import flex.frontend.ui.news.author.AuthorsBody;
import flex.frontend.ui.news.source.SourcesBody;

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
        initHomeButton();
        initSearchButton();
        initSourcesButton();
        initAuthorsButton();
        initArticlesButton();
        initMenuButton();

        addComponents(homeButton, sourcesButton, authorsButton, articlesButton, searchButton, menuButton);
    }

    private void initHomeButton() {
        homeButton = new FlexButton(VaadinIcons.HOME);
        homeButton.setSizeUndefined();
        homeButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Page.getCurrent().setLocation("/flex-app");
            }
        });
    }

    private void initSearchButton() {
        searchButton = new FlexButton(VaadinIcons.SEARCH);
        searchButton.setSizeUndefined();
    }

    private void initSourcesButton() {
        sourcesButton = new FlexButton("Sources");
        sourcesButton.setSizeUndefined();
        sourcesButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                //Notification.show("Sources clicked");
                getNewsView().replaceBody(new SourcesBody());
            }
        });
    }

    private void initAuthorsButton() {
        authorsButton = new FlexButton("Authors");
        authorsButton.setSizeUndefined();
        authorsButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                //Notification.show("Authors clicked");
                getNewsView().replaceBody(new AuthorsBody());
            }
        });
    }

    private void initArticlesButton() {
        articlesButton = new FlexButton("Articles");
        articlesButton.setSizeUndefined();
        articlesButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                //Notification.show("Articles clicked");
                getNewsView().replaceBody(new ArticlesBody());
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

    public FlexButton getHomeButton() {
        return homeButton;
    }

    public FlexButton getSearchButton() {
        return searchButton;
    }

    public FlexButton getSourcesButton() {
        return sourcesButton;
    }

    public FlexButton getMenuButton() {
        return menuButton;
    }

    public FlexButton getAuthorsButton() {
        return authorsButton;
    }

    public FlexButton getArticlesButton() {
        return articlesButton;
    }
    
    

}
