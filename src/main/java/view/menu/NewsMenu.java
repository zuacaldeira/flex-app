/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.menu;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import data.DataProviderType;
import db.FlexUser;
import ui.NewsUI;
import view.body.NewsBody;
import view.logo.FlexLogo;

/**
 *
 * @author zua
 */
public class NewsMenu extends HorizontalLayout implements CanPopulate {

    private static final long serialVersionUID = 8366211712669711650L;

    private final FlexUser user;

    private FlexLogo logo;
    private NewsMenuBar menuBar;
    private TextField searchBox;
    //private BooksButton booksButton;
    

    public NewsMenu(FlexUser user) {
        this.user = user;
        initLogo();
        initMenuBar();
        initSearchBox();
        //initBooksButton();
        super.setWidth("100%");
        super.setMargin(false);
        super.addComponent(logo);
        super.addComponent(menuBar);
        //super.addComponent(booksButton);
        super.addComponent(searchBox);
        super.setExpandRatio(logo, .2f);
        super.setExpandRatio(menuBar, .6f);
        super.setExpandRatio(searchBox, .2f);
        super.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(menuBar, Alignment.MIDDLE_CENTER);
        //super.setComponentAlignment(booksButton, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(searchBox, Alignment.MIDDLE_CENTER);
        super.setStyleName("flex-menu");
    }
    
    private void initSearchBox() {
        searchBox = new TextField();
        searchBox.setCaptionAsHtml(true);
        searchBox.setPlaceholder("Search");
        searchBox.setStyleName("search-box");
        searchBox.addValueChangeListener(e -> {
            Notification.show("Clicked on search " + e.getValue());
            NewsBody body = getBody();
            body.populate(DataProviderType.SEARCH, e.getValue());
        });
    }
    
    /*private void initBooksButton() {
        booksButton = new BooksButton();
        booksButton.addClickListener(e -> {
            getUI().getNavigator().navigateTo(FlexViews.BOOKS);
        });
    }*/
    
    private NewsBody getBody() {
        return ((NewsUI) UI.getCurrent()).getMainView().getBody();
    }

    public FlexUser getUser() {
        return user;
    }

    public FlexLogo getLogo() {
        return logo;
    }

    public NewsMenuBar getMenuBar() {
        return menuBar;
    }

    private void initMenuBar() {
        this.menuBar = new NewsMenuBar(user);
    }

    private void initLogo() {
        logo = new FlexLogo();
    }

    @Override
    public void populate() {
        menuBar.populate();
    }

}