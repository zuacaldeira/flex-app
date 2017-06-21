/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news;

import flex.frontend.ui.FlexButton;
import flex.frontend.ui.crud.CrudMenu;
import org.utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class NewsPublishersMenu extends CrudMenu {

    public NewsPublishersMenu() {
    }

    @Override
    protected void addActions() {
        super.addActions(); 
        addPublishersButtons();
    }

    private void addPublishersButtons() {
        Iterable<String> publishers = ServiceLocator.getInstance().findSourcesService().findNames();
        publishers.forEach(p -> {
            FlexButton pButton = new FlexButton(p);
            addComponent(pButton);
            pButton.addClickListener(event -> {
                getMainView().replaceBody(new NewsPublisherArticlesBody(p));
                setSelected(pButton);
            });
        });
    }
    
}
