/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.menu;

import form.FlexForm;
import org.vaadin.addons.searchbox.SearchBox;

/**
 *
 * @author zua
 */
public class NewsSearchForm extends FlexForm {

    private final SearchBox searchBox;

    public NewsSearchForm() {
        searchBox = new SearchBox("News Search Tool", SearchBox.ButtonPosition.LEFT);
        addComponent(searchBox);
        setSizeFull();
    }

    public SearchBox getSearchBox() {
        return searchBox;
    }

}
