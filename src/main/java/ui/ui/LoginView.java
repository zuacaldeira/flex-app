/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;
import form.LoginForm;

/**
 *
 * @author zua
 */
public class LoginView extends VerticalLayout implements View {

    private static final long serialVersionUID = 3163305215433154572L;
    private LoginForm form;

    public LoginView() {
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        form = new LoginForm();
        super.addComponent(form);
        super.setSizeFull();
        super.setComponentAlignment(form, Alignment.MIDDLE_CENTER);
    }

}
