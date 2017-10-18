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
import ui.FlexAppUI;
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
        super.setSizeFull();
        super.setHeightUndefined();
        //super.setMargin(true);
    }

    public SearchBox getSearchBox() {
        return searchBox;
    }

    private void initSearchBox() {
        searchBox = new SearchBox("", SearchBox.ButtonPosition.RIGHT);
        searchBox.setButtonJoined(true);
        searchBox.setSizeFull();
        searchBox.setStyleName("search-box");
        searchBox.addSearchListener(e -> {
            Notification.show("Clicked on search " + e.getSearchTerm());
            FlexBody body = getBody();
            body.initBodyUpdaterThread(DataProviderType.SEARCH, e.getSearchTerm());
        });
    }
    
    
    public FlexBody getBody() {
        return ((FlexAppUI)getUI()).getContent().getBody();
    }

}
