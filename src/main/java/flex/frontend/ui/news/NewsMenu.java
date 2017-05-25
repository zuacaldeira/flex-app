/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import flex.frontend.ui.FlexButton;
import flex.frontend.ui.FlexMenu;
import flex.frontend.ui.news.article.SingleFieldDialog;

/**
 *
 * @author zua
 */
public class NewsMenu extends FlexMenu {

    private FlexButton homeButton;
    private FlexButton searchButton;
    
    private FlexButton selected;
    private final AbstractOrderedLayout actions;
    
    public NewsMenu() {
        initHomeButton();
        initSearchButton();
        actions = new HorizontalLayout(homeButton, searchButton);
        actions.setSizeUndefined();
        actions.setMargin(false);
        actions.setSpacing(false);
        super.addComponent(actions);
    }

    private void initHomeButton() {
        homeButton = new FlexButton(VaadinIcons.HOME);
        homeButton.setSizeUndefined();
        homeButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                updateSelected(homeButton);
                Page.getCurrent().setLocation("/flex-app");
            }

        });
    }

    private void updateSelected(FlexButton flexButton) {
        if(selected != null) {
            selected.removeStyleName("selected");
        }
        selected = flexButton;
        selected.addStyleName("selected");
    }

    private void initSearchButton() {
        searchButton = new FlexButton(VaadinIcons.SEARCH);
        searchButton.setSizeUndefined();
        searchButton.addClickListener(event -> {
            SingleFieldDialog dialog = new SingleFieldDialog("Search");
            Window w = new FlexWindow("Search", dialog);
            UI.getCurrent().addWindow(w);
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


}
