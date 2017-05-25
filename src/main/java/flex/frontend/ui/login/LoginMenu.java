/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.login;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.ui.AbstractOrderedLayout;
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
    private AbstractOrderedLayout actions;
    private FlexButton loginButton;
    
    public LoginMenu() {
        initNewsButton();
        initBantusButton();
        initAboutMeButton();
        initLoginButton();
        actions = new HorizontalLayout(newsButton, bantusButton, aboutMeButton, loginButton);
        actions.setSizeUndefined();
        actions.setMargin(false);
        actions.setSpacing(false);
        super.addComponents(actions);
    }

    private void initNewsButton() {
        newsButton = new FlexButton("News");
        newsButton.setSizeUndefined();
        newsButton.addClickListener(event -> {
            Page.getCurrent().setLocation("/flex-app/news");
        });
    }

    private void initBantusButton() {
        bantusButton = new FlexButton("Bantu");
        bantusButton.setSizeUndefined();
        bantusButton.addClickListener(event -> {
            Page.getCurrent().setLocation("/flex-app/bantus");
        });
    }

    private void initAboutMeButton() {
        aboutMeButton = new FlexButton("About Me");
        aboutMeButton.setSizeUndefined();
        aboutMeButton.addClickListener(event -> {
            ((LoginUI)getUI()).getLoginView().replaceBody(new AboutMeView());
        });
    }

    private void initLoginButton() {
        loginButton = new FlexButton("Login", VaadinIcons.SIGN_IN);
        loginButton.setSizeUndefined();
        loginButton.addClickListener(event -> {
            ((LoginUI)getUI()).getLoginView().replaceBody(new LoginBody());
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
