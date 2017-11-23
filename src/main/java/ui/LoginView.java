/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.themes.ValoTheme;
import components.LoginForm;
import components.RegisterForm;

/**
 *
 * @author zua
 */
public class LoginView extends HorizontalLayout implements View {

    private static final long serialVersionUID = 3163305215433154572L;
    private TabSheet tabSheet;

    public LoginView() {
        tabSheet = new TabSheet();
        tabSheet.setStyleName(ValoTheme.TABSHEET_CENTERED_TABS);
        tabSheet.setWidth("50%");
        tabSheet.setHeight("50%");
        super.setSizeFull();
        super.addComponent(tabSheet);
        super.setComponentAlignment(tabSheet, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        tabSheet.addTab(new LoginForm(), "LOGIN");
        tabSheet.addTab(new RegisterForm(), "REGISTER");
    }

}
