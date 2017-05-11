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
import flex.frontend.ui.FlexBody;
import flex.frontend.ui.FlexButton;

/**
 *
 * @author zua
 */
public class LoginBody extends FlexBody implements ClickListener {

    private final FlexButton enter;

    public LoginBody() {
        enter = new FlexButton("Enter", VaadinIcons.PLAY_CIRCLE);
        enter.setSizeUndefined();
        enter.addClickListener(this);        
        super.addView(enter);
    }

    public FlexButton getEnter() {
        return enter;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        Page.getCurrent().setLocation("/flex-app/news");
    }
    
}
