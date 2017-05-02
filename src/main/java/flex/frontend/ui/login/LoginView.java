/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.login;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import flex.frontend.ui.FlexButton;

/**
 *
 * @author zua
 */
public class LoginView extends VerticalLayout {
    private final Label welcomeMessage;
    private String WELCOME_MESSAGE = "Welcome to The World's News";
    private final FlexButton enter;

    public LoginView() {
        setStyleName("login-view");
        setSizeUndefined();

        welcomeMessage = new Label(WELCOME_MESSAGE);
        welcomeMessage.setStyleName("welcome-message");
        welcomeMessage.setSizeUndefined();
        
        enter = new FlexButton("Enter", VaadinIcons.PLAY_CIRCLE);
        enter.setSizeUndefined();
        enter.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Page.getCurrent().setLocation("/flex-app/news");
            }
        });
        
        addComponents(welcomeMessage, enter);
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
    }
    
}
