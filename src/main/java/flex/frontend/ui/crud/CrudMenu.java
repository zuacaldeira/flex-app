/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.crud;

import flex.backend.news.db.GraphEntity;
import flex.frontend.ui.FlexButton;
import flex.frontend.ui.FlexMenu;
import flex.frontend.ui.bantu.HomeButton;

/**
 *
 * @author zua
 */
public abstract class CrudMenu<T extends GraphEntity>  extends FlexMenu {

    private FlexButton homeButton;
    private FlexButton createButton;
    private FlexButton searchButton;
    
    public CrudMenu() {
    }

    @Override
    protected void addActions() {
        addHome();
        addSearch();
        addCreate();
    }

    private void addHome() {
        homeButton = new HomeButton();
        addComponent(homeButton);
    }

    private void addSearch() {
        searchButton = new SearchButton();
        addComponent(searchButton);
    }

    private void addCreate() {
        createButton = new FlexButton("create");
        addComponent(createButton);
    }

    public FlexButton getCreateButton() {
        return createButton;
    }

    public FlexButton getHomeButton() {
        return homeButton;
    }

    public FlexButton getSearchButton() {
        return searchButton;
    }

    
}
