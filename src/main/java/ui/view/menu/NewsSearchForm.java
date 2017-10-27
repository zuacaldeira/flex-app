/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.menu;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Notification;
import data.DataProviderType;
import form.FlexForm;
import org.vaadin.addons.searchbox.SearchBox;
import ui.ui.FlexAppUI;
import ui.view.body.FlexBody;

/**
 *
 * @author zua
 */
public class NewsSearchForm extends FlexForm {

    private SearchBox searchBox;

    public NewsSearchForm() {
        initSearchBox();
        super.addComponent(searchBox);
        super.setHeightUndefined();
        super.setMargin(true);
    }

    public SearchBox getSearchBox() {
        return searchBox;
    }

    private void initSearchBox() {
        searchBox = new SearchBox(VaadinIcons.SEARCH, SearchBox.ButtonPosition.RIGHT);
        searchBox.setWidth("100%");
        searchBox.setButtonJoined(true);
        searchBox.setStyleName("search-box");
        searchBox.getSearchField().focus();
        searchBox.getSearchButton().setEnabled(false);
        
        searchBox.addSearchListener(e -> {
            Notification.show("Clicked on search " + e.getSearchTerm());
            FlexBody body = getBody();
            body.populate(DataProviderType.SEARCH, e.getSearchTerm());
        });
    }
    
    
    public FlexBody getBody() {
        return ((FlexAppUI)getUI()).getContent().getBody();
    }

}
