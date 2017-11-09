/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.ui;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;
import form.LoginForm;

/**
 *
 * @author zua
 */
public class LoginView extends VerticalLayout {

    private static final long serialVersionUID = 3163305215433154572L;
    private final LoginForm form;

    public LoginView() {
        form = new LoginForm();
        super.addComponent(form);
        super.setSizeFull();
        setComponentAlignment(form, Alignment.MIDDLE_CENTER);
    }
    
}
