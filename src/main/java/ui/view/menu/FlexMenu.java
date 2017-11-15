/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.menu;

import com.google.common.collect.Lists;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import data.DataProviderType;
import db.FlexUser;
import db.GraphEntity;
import db.NewsArticle;
import java.util.List;
import org.vaadin.addons.autocomplete.generator.SuggestionGenerator;
import org.vaadin.addons.searchbox.SearchBox;
import ui.ui.NewsUI;
import ui.view.body.FlexBody;
import ui.view.logo.FlexLogo;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class FlexMenu extends HorizontalLayout implements CanPopulate, SuggestionGenerator<NewsArticle> {

    private static final long serialVersionUID = 8366211712669711650L;

    private final FlexUser user;

    private FlexLogo logo;
    private FlexMenuBar menuBar;
    private transient SearchBox searchBox;

    public FlexMenu(FlexUser user) {
        this.user = user;
        initLogo();
        initMenuBar();
        initSearchBox();
        super.setSizeFull();
        super.setMargin(false);
        super.addComponent(logo);
        super.addComponent(menuBar);
        super.addComponent(searchBox);
        super.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(menuBar, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(searchBox, Alignment.MIDDLE_CENTER);
        super.setStyleName("flex-menu");
    }
    
    private void initSearchBox() {
        searchBox = new SearchBox(VaadinIcons.SEARCH, SearchBox.ButtonPosition.RIGHT);
        searchBox.setButtonJoined(true);
        searchBox.setStyleName("search-box");
        searchBox.getSearchField().focus();
        searchBox.getSearchField().setStyleName("search-term");
        searchBox.getSearchButton().setStyleName("search-button");
        searchBox.addSearchListener(e -> {
            Notification.show("Clicked on search " + e.getSearchTerm());
            FlexBody body = getBody();
            body.populate(DataProviderType.SEARCH, e.getSearchTerm());
        });
    }
    
    private FlexBody getBody() {
        return ((NewsUI) UI.getCurrent()).getMainView().getBody();
    }

    public FlexUser getUser() {
        return user;
    }

    public FlexLogo getLogo() {
        return logo;
    }

    public FlexMenuBar getMenuBar() {
        return menuBar;
    }

    private void initMenuBar() {
        this.menuBar = new FlexMenuBar(user);
    }

    private void initLogo() {
        logo = new FlexLogo();
    }

    @Override
    public void populate() {
        menuBar.populate();
    }

    @Override
    public List<NewsArticle> apply(String query, Integer limit) {
        return Lists.newLinkedList(ServiceLocator.getInstance().findArticlesService().findArticlesWithText(query));
    }

}
