/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import com.vaadin.ui.Component;
import data.DataProviderType;
import org.ngutu.ui.news.AbstractBody;

/**
 *
 * @author zua
 */
public class WelcomeBody extends AbstractBody {

    private static final long serialVersionUID = 6273025631274336910L;

    public WelcomeBody() {
    }

    @Override
    public void populate(DataProviderType type, String value) {
    }

    @Override
    protected Component createBodyContent() {
        return new AboutUs();
    }
}
