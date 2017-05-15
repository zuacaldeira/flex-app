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

/**
 *
 * @author zua
 */
public class NewsMenu extends FlexMenu {

    private FlexButton homeButton;
    private FlexButton searchButton;
    private FlexButton articlesButton;
    
    private FlexButton selected;
    
    public NewsMenu() {
        initHomeButton();
        initSearchButton();
        initArticlesButton();
        super.addComponents(homeButton, articlesButton, searchButton);
        super.setWidthUndefined();
    }

    private void initHomeButton() {
        homeButton = new FlexButton(VaadinIcons.HOME);
        homeButton.setSizeUndefined();
        homeButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Page.getCurrent().setLocation("/flex-app");
                updateSelected(homeButton);
            }

        });
    }

    private void updateSelected(FlexButton flexButton) {
        if(selected != null) {
            selected.setEnabled(true);
            selected.setStyleName(flexButton.getStyleName());
        }
        selected = flexButton;
        selected.setEnabled(false);
        selected.setStyleName("selected");
    }

    private void initSearchButton() {
        searchButton = new FlexButton(VaadinIcons.SEARCH);
        searchButton.setSizeUndefined();
    }

    private void initArticlesButton() {
        articlesButton = new FlexButton("Articles");
        articlesButton.setSizeUndefined();
        articlesButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                //Notification.show("Articles clicked");
                getNewsView().replaceBody(new ArticlesBody());
                updateSelected(articlesButton);
            }
        });
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


    public FlexButton getArticlesButton() {
        return articlesButton;
    }
    
    

}
