/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import data.DataProviderType;
import org.ngutu.ui.viewproviders.FlexViews;

/**
 *
 * @author zua
 */
public class SearchBox extends HorizontalLayout {

    private static final long serialVersionUID = -6679570785691061512L;
    private TextField text;
    private String searchValue;

    public SearchBox() {
        super.setWidth("70%");
        this.text = new TextField();
        this.text.setSizeFull();

        this.text.setStyleName("search-box");
        this.text.setPlaceholder("Type a keyword, a name, etc...");
        
        this.text.setValueChangeTimeout(5);
        this.text.addValueChangeListener(e -> {
            if (!e.getValue().isEmpty()) {
                searchValue = e.getValue();
            }
        });
        this.text.addShortcutListener(new ShortcutListener("Enter") {
            private static final long serialVersionUID = -6411715953527315282L;
            @Override
            public void handleAction(Object sender, Object target) {
                if(searchValue != null && !searchValue.isEmpty()) {
                    search(searchValue);
                }
            }
        });
        super.addComponents(text);
        super.setComponentAlignment(text, Alignment.MIDDLE_CENTER);
        super.setSpacing(false);
        super.setMargin(false);
    }

    protected void search(String value) {
        Notification.show("Clicked on search " + value);
        value = value.replace(' ', '-');
        UI.getCurrent().getNavigator().navigateTo(FlexViews.NEWS + "/" + DataProviderType.SEARCH.name().toLowerCase() + "/" + value);
    }

}
