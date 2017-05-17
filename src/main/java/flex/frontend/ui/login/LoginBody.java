/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.login;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import flex.frontend.ui.FlexBody;
import flex.frontend.ui.news.FlexTextField;

/**
 *
 * @author zua
 */
public class LoginBody extends FlexBody implements ClickListener {

    private final FormLayout formLayout;

    public LoginBody() {
        formLayout = new FormLayout();
        FlexTextField username = new FlexTextField(VaadinIcons.USER);
        FlexTextField password = new FlexTextField(VaadinIcons.LOCK);
        formLayout.addComponents(username, password);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        Page.getCurrent().setLocation("/flex-app/news");
    }
    
}
