/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news.article;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import flex.frontend.ui.FlexBody;
import flex.frontend.ui.FlexButton;
import flex.frontend.ui.FlexFooter;
import flex.frontend.ui.FlexMenu;
import flex.frontend.ui.news.NewsView;

/**
 *
 * @author zua
 */
public class NewsCategoriesView extends NewsView implements ClickListener {

    public NewsCategoriesView() {
    }

    @Override
    protected FlexMenu createMenu() {
        NewsCategoriesMenu menu = new NewsCategoriesMenu();
        int n = menu.getTopMenu().getComponentCount();
        for(int i = 0; i < n; i++) {
            FlexButton button = (FlexButton) menu.getTopMenu().getComponent(i);
            button.addClickListener(this);
        }
        return menu;
    }

    @Override
    protected FlexBody createBody() {
        return new NewsCategoriesBody(null);
    }

    @Override
    protected FlexFooter createFooter() {
        return new FlexFooter();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        FlexButton button = (FlexButton) event.getButton();
            button.setStyleName("inverted");
        
        Notification.show("Clicked " + button.getCaption());
        replaceBody(new NewsCategoriesBody(button.getCaption()));
    }

}
