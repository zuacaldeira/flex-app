/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import com.vaadin.ui.Component;
import org.ngutu.ui.components.AbstractBody;

/**
 *
 * @author zua
 */
public class WelcomeBody extends AbstractBody {

    private static final long serialVersionUID = 6273025631274336910L;

    public WelcomeBody() {
    }

    @Override
    protected Component createBodyContent() {
        return new AboutUs();
    }
}
