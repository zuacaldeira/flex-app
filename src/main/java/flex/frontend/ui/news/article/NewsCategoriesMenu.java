/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news.article;

import flex.frontend.ui.FlexButton;
import flex.frontend.ui.crud.CrudMenu;
import java.util.Collection;
import org.utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class NewsCategoriesMenu extends CrudMenu {

    private Collection<String> categories;

    public NewsCategoriesMenu() {
    }

    @Override
    protected void addActions() {
        super.addActions();
        addCategoriesButton();
    }

    private void loadCategories() {
        categories = ServiceLocator.getInstance().findSourcesService().findCategories();
    }

    private void addCategoriesButton() {
        loadCategories();
        categories.forEach(cat -> {
            FlexButton button = new FlexButton(cat);
            button.setSizeFull();
            addComponent(button);
            button.addClickListener(event -> {
               super.setSelected(button);
               NewsCategoriesBody newsBody = new NewsCategoriesBody(cat);
               getMainView().replaceBody(newsBody);
            });
        });
    }
    
}
