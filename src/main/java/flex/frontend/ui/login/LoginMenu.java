/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.login;

import com.vaadin.server.Page;
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
    }

    @Override
    protected void addActions() {
        addNewsButton();
        addBantusButton();
        addAboutMeButton();
    }
    
    

    private void addNewsButton() {
        newsButton = new FlexButton("News");
        newsButton.setSizeUndefined();
        newsButton.addClickListener(event -> {
            Page.getCurrent().setLocation("/flex-app/news");
        });
        addComponent(newsButton);
    }

    private void addBantusButton() {
        bantusButton = new FlexButton("Bantu");
        bantusButton.setSizeUndefined();
        bantusButton.addClickListener(event -> {
            Page.getCurrent().setLocation("/flex-app/bantus");
        });
        addComponent(bantusButton);
    }

    private void addAboutMeButton() {
        aboutMeButton = new FlexButton("About Me");
        aboutMeButton.setSizeUndefined();
        aboutMeButton.addClickListener(event -> {
            ((LoginUI)getUI()).getLoginView().replaceBody(new AboutMeView());
        });
        addComponent(aboutMeButton);
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
