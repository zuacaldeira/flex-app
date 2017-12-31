/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import components.SearchButton;
import data.DataProviderType;
import org.ngutu.ui.viewproviders.FlexViews;

/**
 *
 * @author zua
 */
public class SearchBox extends HorizontalLayout {

    private static final long serialVersionUID = -6679570785691061512L;
    private TextField text;
    private SearchButton button;
    private String searchValue;

    public SearchBox() {
        this.text = new TextField();
        this.button = new SearchButton();

        this.text.setVisible(false);
        this.text.setStyleName("search-box");
        this.text.addValueChangeListener(e -> {
            if(!e.getValue().isEmpty()) {
                 searchValue = e.getValue();
            }
        });
        
        this.button.addClickListener(click -> {
            if (!text.isVisible()) {
                text.setVisible(true);
                text.focus();
            }
            else {
                if(searchValue != null) {
                    text.clear();
                    text.setVisible(false);
                    search(searchValue);
                }
            }
        });
        
        super.addComponents(text, button);
        super.setComponentAlignment(text, Alignment.MIDDLE_CENTER);
        super.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
        super.setSpacing(false);
        super.setMargin(false);
    }

    protected void search(String value) {
        Notification.show("Clicked on search " + value);
        value = value.replace(' ', '-');
        UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS + "/" + DataProviderType.SEARCH.name().toLowerCase() + "/" + value);
    }

}
