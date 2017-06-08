/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news;

import com.vaadin.ui.UI;
import flex.frontend.ui.FlexButton;
import flex.frontend.ui.FlexMenu;
import flex.frontend.ui.bantu.HomeButton;
import java.util.Collection;
import org.utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class NewsCategoriesMenu extends FlexMenu {

    private Collection<String> categories;

    public NewsCategoriesMenu() {
    }

    @Override
    protected void addActions() {
        loadCategories();
        addHomeButton();
        addCategoriesButton();
    }

    private void loadCategories() {
        categories = ServiceLocator.getInstance().findSourcesService().findCategories();
    }

    private void addHomeButton() {
        addComponent(new HomeButton());
    }

    private void addCategoriesButton() {
        categories.forEach(cat -> {
            FlexButton button = new FlexButton(cat.toLowerCase());
            button.setSizeFull();
            addComponent(button);
        });
    }
    
}
