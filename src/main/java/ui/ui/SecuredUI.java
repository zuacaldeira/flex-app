/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

/**
 *
 * @author zua
 */
@Theme("mytheme")
public abstract class SecuredUI extends UI {

    private static final long serialVersionUID = 2637212442082775079L;

    @Override
    public void init(VaadinRequest request) {
        if (getSession() != null && getSession().getAttribute("user") != null) {
            setContent(createUIContent());
        }
        else {
            setContent(new LoginView());
        }
    }

    protected abstract Component createUIContent();
}
