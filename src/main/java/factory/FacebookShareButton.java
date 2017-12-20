/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import com.vaadin.icons.VaadinIcons;
import org.ngutu.ui.components.FlexActionButton;

/**
 *
 * @author zua
 */
public class FacebookShareButton extends FlexActionButton {

    private static final long serialVersionUID = -4146017971684793445L;

    public FacebookShareButton() {
        super("Share on Facebook", VaadinIcons.FACEBOOK);
    }
}
