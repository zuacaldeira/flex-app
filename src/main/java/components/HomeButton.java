/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import com.vaadin.icons.VaadinIcons;
import org.ngutu.ui.viewproviders.FlexViews;

/**
 *
 * @author zua
 */
public class HomeButton extends FlexButton {

    private static final long serialVersionUID = -7011599843339184461L;

    public HomeButton() {
        super(FlexCaptions.HOME, VaadinIcons.HOME);
        super.addClickListener(event -> {
            getUI().getNavigator().navigateTo(FlexViews.WELCOME);
        });
    }

}
