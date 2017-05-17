/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.login;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.ui.HorizontalLayout;
import flex.frontend.ui.FlexButton;
import flex.frontend.ui.FlexMenu;

/**
 *
 * @author zua
 */
public class LoginMenu extends FlexMenu {

    private FlexButton newsButton;
    private FlexButton bantusButton;
    private FlexButton aboutMeButton;

    public LoginMenu() {
        initNewsButton();
        initBantusButton();
        initAboutMeButton();
        super.addComponents(new HorizontalLayout(newsButton, bantusButton, aboutMeButton));
    }

    private void initNewsButton() {
        newsButton = new FlexButton("News", VaadinIcons.NEWSPAPER);
        newsButton.setSizeUndefined();
        newsButton.addClickListener(event -> {
            Page.getCurrent().setLocation("/flex-app/news");
        });
    }

    private void initBantusButton() {
        bantusButton = new FlexButton("Bantu", VaadinIcons.GLOBE);
        bantusButton.setSizeUndefined();
        bantusButton.addClickListener(event -> {
            Page.getCurrent().setLocation("/flex-app/bantus");
        });
    }

    private void initAboutMeButton() {
        aboutMeButton = new FlexButton("About Me", VaadinIcons.INFO_CIRCLE_O);
        aboutMeButton.setSizeUndefined();
        aboutMeButton.addClickListener(event -> {
            ((LoginUI)getUI()).getLoginView().replaceBody(new AboutMeView());
        });
    }

    public FlexButton getNewsButton() {
        return newsButton;
    }

    public FlexButton getBantusButton() {
        return bantusButton;
    }

    public FlexButton getAboutMeButton() {
        return aboutMeButton;
    }
    
    
    
}
