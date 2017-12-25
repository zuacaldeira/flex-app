/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.themes.ValoTheme;
import org.ngutu.ui.news.FlexMenuButton;

/**
 *
 * @author zua
 */
public class SearchButton extends FlexMenuButton {

    private static final long serialVersionUID = -7211229433897090893L;

    public SearchButton() {
        super(null, VaadinIcons.SEARCH);
        super.addStyleName(ValoTheme.BUTTON_QUIET);
    }

}
