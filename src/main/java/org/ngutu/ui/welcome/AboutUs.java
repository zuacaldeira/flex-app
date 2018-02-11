/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.welcome;

import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author zua
 */
public class AboutUs extends VerticalLayout implements View {

    private static final long serialVersionUID = -5299532704066300758L;

    public AboutUs() {
        super.addComponents(
                new NgutuMissionCard(),
                new NgutuVisionCard(),
                new NgutuGoalsCard(),
                new NgutuContactCard());
        super.setDefaultComponentAlignment(Alignment.TOP_CENTER);
        super.setSizeFull();
        super.setStyleName("about-us");
    }

}
